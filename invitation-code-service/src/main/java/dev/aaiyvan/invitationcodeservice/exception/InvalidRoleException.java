package dev.aaiyvan.invitationcodeservice.exception;


import lombok.NoArgsConstructor;

/**
 * Exception thrown when an invalid role is provided.
 */
@NoArgsConstructor
public class InvalidRoleException extends IllegalArgumentException {

    /**
     * Constructs a new InvalidRoleException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidRoleException(
            final String message
    ) {
        super(message);
    }

}
