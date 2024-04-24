package dev.aaiyvan.storageservice.service;

import dev.aaiyvan.storageservice.model.entity.MinioFile;
import dev.aaiyvan.storageservice.model.types.ContentSource;

import java.util.Optional;
import java.util.UUID;

/**
 * This interface represents a service for managing MinioFile entities.
 * It provides methods for saving, deleting, and retrieving MinioFile entities.
 */
public interface MinioFileService {

    /**
     * Saves an MinioFile entity.
     *
     * @param minioFile the MinioFile entity to save.
     * @return the saved MinioFile entity.
     */
    MinioFile save(MinioFile minioFile);

    /**
     * Deletes an MinioFile entity by its id.
     *
     * @param id the id of the MinioFile entity to delete.
     */
    void delete(UUID id);

    /**
     * Finds an MinioFile entity by its file name and source.
     *
     * @param fileName the name of the file.
     * @param source the source of the file content.
     * @return an Optional containing the found MinioFile entity, or an empty Optional if no entity was found.
     */
    Optional<MinioFile> getByFileNameAndSource(String fileName, ContentSource source);

}
