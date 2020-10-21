package com.immfly.backend.ms.flight.application.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Unauthorized.class)
    ResponseEntity<Map<String, Object>> handleUnauthorized(Unauthorized exception){
        Map<String, Object> response = new HashMap<>();
        response.put("Reason", exception.getMessage());
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("Reason", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, Object>> handleException(Exception exception){
        Map<String, Object> response = new HashMap<>();
        response.put("Reason", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
