package dev.aaiyvan.newsservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "file_attachment")
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    @Column(name = "file_name")
    String fileName;

    String source;

    String url;

    @ManyToOne
    private News news;

    public FileAttachment(String fileName, String source, String url, News news) {
        this.fileName = fileName;
        this.source = source;
        this.url = url;
        this.news = news;
    }

}
