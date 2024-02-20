package com.inditex.exception;

public class InternalServerException extends RuntimeException {
    //for database connection errors, file system errors, etc.).

    private String message;

    public InternalServerException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
