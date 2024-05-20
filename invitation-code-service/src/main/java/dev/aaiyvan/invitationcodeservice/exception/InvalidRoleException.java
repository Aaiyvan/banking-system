package dev.aaiyvan.invitationcodeservice.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidRoleException extends IllegalArgumentException {

    public InvalidRoleException(
            final String message
    ) {
        super(message);
    }

}
