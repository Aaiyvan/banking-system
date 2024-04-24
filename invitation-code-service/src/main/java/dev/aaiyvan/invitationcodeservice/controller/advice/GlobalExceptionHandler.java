package dev.aaiyvan.invitationcodeservice.controller.advice;

import dev.aaiyvan.invitationcodeservice.exception.InvalidCodeException;
import dev.aaiyvan.invitationcodeservice.exception.InvalidRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRoleException.class)
    ProblemDetail handleInvalidRoleException(
            final Exception exception
    ) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidCodeException.class)
    ProblemDetail handleInvalidCodeException(
            final Exception exception
    ) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}