package dev.aaiyvan.storageservice.model.entity;

import dev.aaiyvan.storageservice.model.types.ContentSource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class represents a file stored in Minio.
 * It is annotated with @Entity, indicating that it is a JPA entity.
 * Lombok annotations are used to reduce boilerplate code.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinioFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fileName;

    @CreationTimestamp
    private LocalDateTime uploadedTime;

    @Enumerated(EnumType.STRING)
    private ContentSource source;

    private String target;

}
