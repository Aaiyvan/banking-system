package dev.aaiyvan.customerservice.model.dto;

import dev.aaiyvan.customerservice.model.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    @NotBlank(message = "firstname cannot be blank")
    String firstname;

    @NotBlank(message = "lastname cannot be blank")
    String lastname;

    @NotBlank(message = "username cannot be blank")
    String username;

    @Email(message = "email should be valid")
    String email;

    @NotNull(message = "gender cannot be null")
    Gender gender;

}
