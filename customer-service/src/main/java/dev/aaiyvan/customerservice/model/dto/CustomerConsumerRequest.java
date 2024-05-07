package dev.aaiyvan.customerservice.model.dto;

import dev.aaiyvan.customerservice.model.enums.Gender;

public record CustomerConsumerRequest(
        String firstname,
        String lastname,
        String email,
        Gender gender
) {
}
