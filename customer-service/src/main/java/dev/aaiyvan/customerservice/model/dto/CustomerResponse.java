package dev.aaiyvan.customerservice.model.dto;

import dev.aaiyvan.customerservice.model.enums.Gender;

public record CustomerResponse(
        String firstname,
        String lastname,
        String username,
        String email,
        Gender gender,
        String avatar
) {
}
