package com.inditex.core.validation;

import com.inditex.core.model.CurrencySymbolEnum;
import com.inditex.core.exception.InvalidCurrencyException;

public class CurrencyValidator {

    private CurrencyValidator() {

    }

    public static void validateCurrency(String currency) {
        if (!CurrencySymbolEnum.isValidCurrency(currency)) {
            throw new InvalidCurrencyException("Invalid currency");
        }
    }
}
