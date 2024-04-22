package dev.aaiyvan.invitationcodeservice.exception;

import lombok.NoArgsConstructor;

/**
 * Exception thrown when an invalid invite code is provided.
 */
@NoArgsConstructor
public class InvalidCodeException extends RuntimeException {

    /**
     * Constructs a new InvalidCodeException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidCodeException(
            final String message
    ) {
        super("Invalid invite code: " + message);
    }

}