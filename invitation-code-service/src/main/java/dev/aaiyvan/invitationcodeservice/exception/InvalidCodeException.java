package dev.aaiyvan.invitationcodeservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCodeException extends RuntimeException {

    public InvalidCodeException(
            final String message
    ) {
        super("Invalid invite code: " + message);
    }

}