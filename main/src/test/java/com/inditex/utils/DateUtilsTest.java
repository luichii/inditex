package com.inditex.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilsTest {
    public static LocalDateTime convertStringToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        return LocalDateTime.parse(dateString, formatter);
    }
}
