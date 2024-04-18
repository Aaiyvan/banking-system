package dev.aaiyvan.customerservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(
            final String message
    ) {
        super(message);
    }

}
