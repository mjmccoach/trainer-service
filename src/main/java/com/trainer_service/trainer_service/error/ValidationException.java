package com.trainer_service.trainer_service.error;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }
}
