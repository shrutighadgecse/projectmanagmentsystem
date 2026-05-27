package com.example.demo.exception;

public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateFailedException(String string) {
    }
}
