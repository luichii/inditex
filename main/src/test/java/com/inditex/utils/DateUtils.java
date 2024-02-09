package com.inditex.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDateTime convert(String dateTimeString) {
        String dateTimeStringNew = dateTimeString.replace("\"", "");

        // Check if the string contains a timezone offset
        if (dateTimeStringNew.contains("+") || dateTimeStringNew.contains("-")) {
            // Pattern includes optional parts for nanoseconds and offset
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSSSSS][XXX]");
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeStringNew, formatter);
            offsetDateTime = offsetDateTime.toInstant().atOffset(ZoneOffset.UTC);
            return offsetDateTime.toLocalDateTime();

        } else {
            // Adjust the pattern to allow for optional 'Z' at the end
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss['Z']");
            return LocalDateTime.parse(dateTimeStringNew, formatter);
        }
    }
}
