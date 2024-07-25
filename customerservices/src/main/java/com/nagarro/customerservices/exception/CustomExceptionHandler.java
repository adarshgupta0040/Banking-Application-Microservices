package com.nagarro.customerservices.exception;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
        
		Map<String, String> errorResponse = new HashMap<>();
		
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("code", HttpStatus.BAD_REQUEST.toString());
        errorResponse.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(errorResponse);
    }
}