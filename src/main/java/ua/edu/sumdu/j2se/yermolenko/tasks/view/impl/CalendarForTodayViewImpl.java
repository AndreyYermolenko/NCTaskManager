package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.CalendarForTodayController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.CalendarForTodayView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

/**
 * Class CalendarForTodayImplView realizes view calendar for today.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class CalendarForTodayViewImpl implements CalendarForTodayView {
    private BufferedReader reader;
    private CalendarForTodayController calendarForTodayController;
    private final static Logger logger = LogManager.getLogger(CalendarForTodayViewImpl.class);

    /**
     * Method setCalendarForTodayController sets the calendarForTodayController of this CalendarForTodayImplView object.
     *
     *
     *
     * @param calendarForTodayController the calendarForTodayController of this CalendarForTodayImplView object.
     *
     */
    public void setCalendarForTodayController(CalendarForTodayController calendarForTodayController) {
        this.calendarForTodayController = calendarForTodayController;
    }

    /**
     * Method doWork realizes view calendar for today.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        this.reader = reader;
        calendarForTodayController.doWork(list);
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
            logger.error("Problem display calendar for today", e);
        }
    }
}
