package dev.aaiyvan.storageservice.repository;

import dev.aaiyvan.storageservice.model.entity.MinioFile;
import dev.aaiyvan.storageservice.model.types.ContentSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * This interface represents a repository for managing MinioFile entities.
 * It extends JpaRepository, which provides methods for performing CRUD operations.
 */
public interface MinioFileRepository extends JpaRepository<MinioFile, UUID> {

    /**
     * Finds an MinioFile entity by its file name and source.
     *
     * @param fileName the name of the file.
     * @param source the source of the file content.
     * @return an Optional containing the found MinioFile entity, or an empty Optional if no entity was found.
     */
    Optional<MinioFile> findByFileNameAndSource(String fileName, ContentSource source);

}
