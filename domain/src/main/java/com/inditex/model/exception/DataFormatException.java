package com.inditex.model.exception;

public class DataFormatException extends RuntimeException {
    private String message;

    public DataFormatException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}