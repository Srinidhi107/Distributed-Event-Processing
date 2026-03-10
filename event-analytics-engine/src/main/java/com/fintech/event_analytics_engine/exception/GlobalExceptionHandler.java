package com.fintech.event_analytics_engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fintech.event_analytics_engine.dto.ApiResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Transaction Not Found
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(TransactionNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiResponse<>(404, ex.getMessage(), null),
                HttpStatus.NOT_FOUND
        );
    }

    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new ApiResponse<>(400, errors, null),
                HttpStatus.BAD_REQUEST
        );
    }
}