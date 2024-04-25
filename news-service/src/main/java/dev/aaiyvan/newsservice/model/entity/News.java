package dev.aaiyvan.newsservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    UUID id;

    String title;

    String content;

    @CreationTimestamp
    @Column(name = "published_date")
    LocalDateTime publishedDate;

    @Column(name = "tag")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "news_tags")
    List<String> tags;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    Set<FileAttachment> files;

}
