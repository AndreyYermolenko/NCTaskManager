package ua.edu.sumdu.j2se.yermolenko.tasks.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ServiceMethods {
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

    /*Метод принимает на вход интервал-строку
    * в формате дни:часы:минуты. Разделяет строку
    * на дни, часы и минуты, преобразует их
    * в целые числа и возвращает интервал в секундах.*/
    public static int parseInterval(String interval) {
        String [] arrDate = interval.split(":");
        if (arrDate.length != 3) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(arrDate[0]) * 86400
                + Integer.parseInt(arrDate[1]) * 3600
                + Integer.parseInt(arrDate[2]) * 60;
    }

    public static int parseID(String inputNumber) throws IllegalArgumentException {
        int ID = Integer.parseInt(inputNumber);
        if (ID >= 100 && ID <= 999) {
            return ID;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
