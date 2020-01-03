package ua.edu.sumdu.j2se.yermolenko.tasks.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class ServiceMethods realizes services methods.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ServiceMethods {
    /**
     * Method validateNumberMenu realizes validate number menu.
     *
     * @param number of type int
     * @param count of type int
     * @return boolean
     */
    public static boolean validateNumberMenu(int number, int count) {
        return number >= 1 && number <= count;
    }

    /**
     * Method validateNumberMenu realizes validate number menu.
     *
     * @param line of type String
     * @param regEx of type String
     * @return boolean
     */
    public static boolean validateNumberMenu(String line, String regEx) {
        return line.matches(regEx);
    }

    /**
     * Method splitDate realizes split input date.
     *
     * @param line of type String
     * @return String[]
     * @throws IllegalArgumentException when
     */
    public static String [] splitDate(String line) throws IllegalArgumentException {
        String [] arrDate = line.split("\\$");
        if (arrDate.length != 1 && arrDate.length != 3) {
            throw new IllegalArgumentException();
        }
        return arrDate;
    }

    /**
     * Method parseDateTime realizes parse input date and time.
     *
     * @param dateString of type String
     * @return LocalDateTime
     * @throws DateTimeParseException when
     */
    public static LocalDateTime parseDateTime(String dateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * The method accepts an interval string as days: hours: minutes.
     * Divides the string into days, hours and minutes,
     * converts them to integers and returns the interval in seconds.
     *
     * @param interval of type String
     * @return int
     */
    public static int parseInterval(String interval) {
        String [] arrDate = interval.split(":");
        if (arrDate.length != 3) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(arrDate[0]) * 86400
                + Integer.parseInt(arrDate[1]) * 3600
                + Integer.parseInt(arrDate[2]) * 60;
    }

    /**
     * Method parseID realizes parse input ID.
     *
     * @param inputNumber of type String
     * @return int
     * @throws IllegalArgumentException when
     */
    public static int parseID(String inputNumber) throws IllegalArgumentException {
        int ID = Integer.parseInt(inputNumber);
        if (ID >= 100 && ID <= 999) {
            return ID;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method parseBoolean realizes input boolean value.
     *
     * @param activity of type String
     * @return boolean
     */
    public static boolean parseBoolean(String activity) {
        if(!activity.equals("true") && !activity.equals("false")) {
            throw new IllegalArgumentException();
        }
        return Boolean.parseBoolean(activity);
    }
}
