package ua.edu.sumdu.j2se.yermolenko.tasks.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateData {
    public static boolean validateNumberMenu(int number, int count) {
        return number >= 1 && number <= count;
    }

    public static boolean validateNumberMenu(String line, String regEx) {
        return line.matches(regEx);
    }

    public static String [] splitDate(String line) throws IllegalArgumentException {
        String [] arrDate = line.split("\\$");
        if (arrDate.length != 1 && arrDate.length != 3) {
            throw new IllegalArgumentException();
        }
        return arrDate;
    }

        public static LocalDateTime parseDateTime(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}
