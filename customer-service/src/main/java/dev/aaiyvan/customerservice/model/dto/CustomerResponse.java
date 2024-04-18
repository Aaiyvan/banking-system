package dev.aaiyvan.customerservice.model.dto;

import dev.aaiyvan.customerservice.model.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse{

    String firstname;

    String lastname;

    String username;

    String email;

    Gender gender;

}
