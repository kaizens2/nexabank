package com.nexabank.customerservice.exception;

import com.nexabank.customerservice.exception.custom.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
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

    @ExceptionHandler(UserAlreadyExistException.class)
    public ProblemDetail userExistValidation(
            UserAlreadyExistException ex) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.FOUND,
                        "User already exists");
        problem.setProperty("timestamp", Instant.now());
        problem.setProperty("errors", ex.getMessage());
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
}