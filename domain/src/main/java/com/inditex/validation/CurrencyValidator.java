package com.inditex.validation;

import com.inditex.model.CurrencySymbolEnum;

public class CurrencyValidator {
    public static void validateCurrency(String currency) {
        if (!CurrencySymbolEnum.isValidCurrency(currency)) {
            throw new com.inditex.validation.InvalidCurrencyException("Invalid currency");
        }
    }
}
