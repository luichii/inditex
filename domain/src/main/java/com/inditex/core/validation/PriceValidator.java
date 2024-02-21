package com.inditex.core.validation;

import com.inditex.core.exception.InvalidPriceException;

public class PriceValidator {
    public static void validatePrice(Float price) {
        if (price == null || price <= 0) {
            throw new InvalidPriceException("Invalid price");
        }
    }
}
