package com.inditex.core.exception;

public class DataFormatException extends RuntimeException {
    private String message;

    public DataFormatException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
