package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<String> handleUpdateFailedException(UpdateFailedException e) {
        e.printStackTrace(); // Log the exception for debugging
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error updating form: " + e.getMessage());
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleValidationExceptions(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Validation failed: " + e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleMultipartException(MultipartException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Multipart request failed: " + e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFound(NoHandlerFoundException e) {
        // Return 404 instead of 500 for static resources not found (like favicon.ico)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Resource not found: " + e.getRequestURL());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Server error: " + e.getMessage());
    }
}