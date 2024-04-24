package dev.aaiyvan.storageservice.service.impl;

import dev.aaiyvan.storageservice.model.entity.MinioFile;
import dev.aaiyvan.storageservice.model.types.ContentSource;
import dev.aaiyvan.storageservice.repository.MinioFileRepository;
import dev.aaiyvan.storageservice.service.MinioFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * This class implements the MinioFileService interface.
 * It is annotated with @Service, indicating that it is a Spring service.
 * It uses Lombok's @RequiredArgsConstructor to automatically generate a constructor with required fields.
 */
@Service
@RequiredArgsConstructor
public class MinioFileServiceImpl implements MinioFileService {

    /**
     * The repository for managing MinioFile entities.
     */
    private final MinioFileRepository minioFileRepository;

    /**
     * Saves an MinioFile entity.
     *
     * @param minioFile the MinioFile entity to save.
     * @return the saved MinioFile entity.
     */
    @Override
    public MinioFile save(
            final MinioFile minioFile
    ) {
        return minioFileRepository.save(minioFile);
    }

    /**
     * Deletes an MinioFile entity by its id.
     *
     * @param id the id of the MinioFile entity to delete.
     */
    @Override
    public void delete(
            final UUID id
    ) {
        minioFileRepository.deleteById(id);
    }

    /**
     * Finds an MinioFile entity by its file name and source.
     *
     * @param fileName the name of the file.
     * @param source the source of the file content.
     * @return an Optional containing the found MinioFile entity, or an empty Optional if no entity was found.
     */
    @Override
    public Optional<MinioFile> getByFileNameAndSource(
            final String fileName,
            final ContentSource source
    ) {
        return minioFileRepository.findByFileNameAndSource(fileName, source);
    }

}
