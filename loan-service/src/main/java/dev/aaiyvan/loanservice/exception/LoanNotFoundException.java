package dev.aaiyvan.loanservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(
            final String message
    ) {
        super(message);
    }

}
