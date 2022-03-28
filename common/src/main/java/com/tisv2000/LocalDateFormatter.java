package com.tisv2000;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class LocalDateFormatter {
    public static final String PATTERN = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
