package com.nexabank.accountservice.exceptions;

import com.nexabank.accountservice.exceptions.custom.AccountNumberNotFoundException;
import com.nexabank.accountservice.exceptions.custom.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(
            MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                        "Validation failed");
        problem.setProperty("timestamp", Instant.now());
        problem.setProperty("errors", ex.getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList());
        return problem;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(
            IllegalArgumentException ex) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFoundException(
            CustomerNotFoundException ex) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(AccountNumberNotFoundException.class)
    public ProblemDetail handleAccountNumberNotFoundException(
            AccountNumberNotFoundException ex) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
