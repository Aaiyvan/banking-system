package dev.aaiyvan.storageservice.service.impl;

import dev.aaiyvan.storageservice.exception.FileDownloadException;
import dev.aaiyvan.storageservice.exception.InvalidFileTypeException;
import dev.aaiyvan.storageservice.exception.InvalidUrlException;
import dev.aaiyvan.storageservice.model.dto.FileUploadResponse;
import dev.aaiyvan.storageservice.model.entity.MinioFile;
import dev.aaiyvan.storageservice.model.types.ContentSource;
import dev.aaiyvan.storageservice.service.MinioFileService;
import dev.aaiyvan.storageservice.service.StorageService;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static dev.aaiyvan.storageservice.model.types.ContentSource.*;

/**
 * This class implements the StorageService interface.
 * It is annotated with @Service, indicating that it is a Spring service.
 * It uses Lombok's @RequiredArgsConstructor to automatically generate a constructor with required fields.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    /**
     * The Minio client.
     */
    private final MinioClient minio;

    /**
     * The service for managing MinioFile entities.
     */
    private final MinioFileService minioFileService;

    /**
     * Creates a new Minio bucket with the specified name, or returns an existing one.
     *
     * @param bucketName the name of the bucket.
     */
    @SneakyThrows
    private void createBucket(
            final String bucketName
    ) {
        boolean found = minio.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!found) {
            minio.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    /**
     * Uploads a list of files to Minio bucket.
     *
     * @param source the source of the file content.
     * @param id the id of the target.
     * @param files the files to upload.
     * @return an array of responses from the file upload operations.
     */
    @Override
    @SneakyThrows
    public FileUploadResponse[] uploadFile(
            final String source,
            final String id,
            final List<MultipartFile> files
    ) {
        ContentSource contentSource = valueOf(source.toUpperCase());
        String bucketName = contentSource.getBucketName();

        if (contentSource == USER_PROFILE_IMAGE) {
            for (MultipartFile file : files) {
                if (!isImageFile(file)) {
                    throw new InvalidFileTypeException("image");
                }
            }
        }

        createBucket(bucketName);

        List<FileUploadResponse> responses = new ArrayList<>();
        for (MultipartFile multipartFile : files) {

            File fileToUpload = convertMultiPartFileToFile(multipartFile);

            InputStream fileInputStream = multipartFile.getInputStream();
            String filename = generateFileName(multipartFile);

            minio.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .build());
            log.info("Uploading file with filename {}", filename);

            if (!fileToUpload.delete()) {
                log.error("Could not delete temporary file {}", fileToUpload.getAbsolutePath());
            }

            minioFileService.save(MinioFile.builder()
                    .fileName(filename)
                    .source(contentSource)
                    .target(id)
                    .build());

            responses.add(new FileUploadResponse(
                    filename,
                    source,
                    minio.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .method(Method.POST)
                            .build())
            ));
        }

        return responses.toArray(new FileUploadResponse[0]);
    }

    /**
     * Checks if a file is an image file.
     *
     * @param file the file to check.
     * @return true if the file is an image file, false otherwise.
     */
    private boolean isImageFile(
            final MultipartFile file
    ) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    /**
     * Deletes a file from Minio bucket.
     *
     * @param source the source of the file content.
     * @param fileName the name of the file.
     */
    @Override
    @SneakyThrows
    public void deleteFile(
            final String source,
            final String fileName
    ) {
        ContentSource contentSource = valueOf(source.toUpperCase());
        String bucketName = contentSource.getBucketName();
        Optional<MinioFile> minioFile = minioFileService.getByFileNameAndSource(fileName, contentSource);
        minioFile.ifPresent(file -> minioFileService.delete(file.getId()));
        minio.removeObject(RemoveObjectArgs
                .builder()
                .bucket(bucketName)
                .object(fileName)
                .build()
        );
    }

    /**
     * Extracts the source and file name from a URL.
     *
     * @param url the URL to extract from.
     * @return an array containing the source and file name.
     */
    @Override
    public String[] extractSourceAndFileName(
            final String url
    ) {
        try {
            URL fileUrl = new URL(url);
            String fileName = fileUrl.getPath().substring(fileUrl.getPath().substring(1).indexOf("/") + 2);
            String bucketName = fileUrl.getPath().substring(1, fileUrl.getPath().substring(1).indexOf("/") + 1);
            return new String[]{fromBucketName(bucketName).toString(), fileName};
        } catch (MalformedURLException e) {
            throw new InvalidUrlException("Invalid URL: " + e);
        }
    }

    /**
     * Downloads a file from Minio bucket.
     *
     * @param source the source of the file content.
     * @param fileName the name of the file.
     * @return the downloaded file as a byte array.
     */
    @Override
    @SneakyThrows
    public byte[] downloadFile(
            final String source,
            final String fileName
    ) {
        ContentSource contentSource = valueOf(source.toUpperCase());

        try (InputStream inputStream = minio.getObject(GetObjectArgs.builder()
                .bucket(contentSource.getBucketName())
                .object(fileName)
                .build());
        ) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("Error downloading file", e);
            throw new FileDownloadException(e.getMessage());
        }
    }

    /**
     * Converts a MultipartFile to a File.
     *
     * @param file the MultipartFile to convert.
     * @return the converted File.
     */
    public static File convertMultiPartFileToFile(
            final MultipartFile file
    ) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
            throw new RuntimeException("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    /**
     * Generates a MultipartFile name.
     *
     * @param file the MultipartFile to convert.
     * @return the string filename.
     */
    private String generateFileName(
            final MultipartFile file
    ) {
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }

    /**
     * Generates a MultipartFile extension.
     *
     * @param file the MultipartFile to convert.
     * @return the string file extension.
     */
    private String getExtension(
            final MultipartFile file
    ) {
        return file.getOriginalFilename()
                .substring(file.getOriginalFilename()
                        .lastIndexOf(".") + 1);
    }

}
