package com.rsakin.vocid22tracker.exception.handler;

import com.rsakin.vocid22tracker.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    private static final String ERROR_MESSAGE = "error_message";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(final NotFoundException exception) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_MESSAGE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(final Exception exception) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_MESSAGE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

}