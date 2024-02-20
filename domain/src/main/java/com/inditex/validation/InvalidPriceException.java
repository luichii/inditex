package com.inditex.validation;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException(String message) {
        super(message);
    }
}
