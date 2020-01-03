package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.CalendarForWeekController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.CalendarForWeekView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

/**
 * Class CalendarForWeekImplView realizes view calendar for week.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class CalendarForWeekImplView implements CalendarForWeekView {
    private BufferedReader reader;
    private CalendarForWeekController calendarForWeekController;
    private final static Logger logger = LogManager.getLogger(CalendarForWeekImplView.class);


    /**
     * Method setCalendarForWeekController sets the calendarForWeekController of this CalendarForWeekImplView object.
     *
     *
     *
     * @param calendarForWeekController the calendarForWeekController of this CalendarForWeekImplView object.
     *
     */
    public void setCalendarForWeekController(CalendarForWeekController calendarForWeekController) {
        this.calendarForWeekController = calendarForWeekController;
    }

    /**
     * Method doWork realizes view calendar for week.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        this.reader = reader;
        calendarForWeekController.doWork(list);
    }

    /**
     * Method doShow shows result.
     *
     * @param calendar of type Map<LocalDateTime, Set<Task>>
     */
    @Override
    public void doShow(Map<LocalDateTime, Set<Task>> calendar) {
        for (Map.Entry pair: calendar.entrySet()) {
            printText(pair.getKey() + " " + pair.getValue());
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
