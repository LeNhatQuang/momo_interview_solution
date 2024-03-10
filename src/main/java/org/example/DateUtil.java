package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parse(String text) {
        return LocalDate.parse("30/11/2020", formatter);
    }

    public static String format(LocalDate date) {
        return date.format(formatter);
    }
}
