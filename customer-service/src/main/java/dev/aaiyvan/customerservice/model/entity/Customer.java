package dev.aaiyvan.customerservice.model.entity;

import dev.aaiyvan.customerservice.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "customer", name = "t_customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
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

    @Column(name = "c_account_id")
    UUID accountId;

}
