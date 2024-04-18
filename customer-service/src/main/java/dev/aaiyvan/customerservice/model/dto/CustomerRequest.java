package dev.aaiyvan.customerservice.model.dto;

import dev.aaiyvan.customerservice.model.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    @NotBlank(message = "firstname cannot be blank.")
    String firstname;

    @NotBlank(message = "lastname cannot be blank.")
    String lastname;

    @NotBlank(message = "username cannot be blank.")
    String username;

    @Email(message = "email should be as 'example@mail.com'.")
    String email;

    @NotNull(message = "gender cannot be blank.")
    Gender gender;

}
