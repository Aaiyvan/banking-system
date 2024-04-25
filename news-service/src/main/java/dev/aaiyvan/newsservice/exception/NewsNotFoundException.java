package dev.aaiyvan.newsservice.exception;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException(
            final UUID id
    ) {
        super("News with id %s not found".formatted(id));
    }

}