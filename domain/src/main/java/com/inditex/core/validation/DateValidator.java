package com.inditex.core.validation;

import com.inditex.core.exception.InvalidDateRangeException;

import java.time.LocalDateTime;

public class DateValidator {
    private DateValidator() {
    }

    public static void validateDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new InvalidDateRangeException("End date cannot be before start date");
        }
    }
}
