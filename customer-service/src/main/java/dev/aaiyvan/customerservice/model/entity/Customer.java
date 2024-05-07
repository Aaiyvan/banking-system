package dev.aaiyvan.customerservice.model.entity;

import dev.aaiyvan.customerservice.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "c_firstname")
    String firstname;

    @Column(name = "c_lastname")
    String lastname;

    @Column(name = "c_username")
    String username;

    @Column(name = "c_email")
    String email;

    @Column(name = "c_gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "c_avatar")
    String avatar;

}
