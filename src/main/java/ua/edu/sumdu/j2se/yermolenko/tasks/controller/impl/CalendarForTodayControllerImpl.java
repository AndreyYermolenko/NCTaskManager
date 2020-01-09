package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.CalendarForTodayController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.CalendarForTodayView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.calendar;

/**
 * Class CalendarForTodayImplController realizes view calendar for today.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class CalendarForTodayControllerImpl implements CalendarForTodayController {
    private CalendarForTodayView calendarForTodayView;

    /**
     * Method setCalendarForTodayView sets the calendarForTodayView of this CalendarForTodayImplController object.
     *
     *
     *
     * @param calendarForTodayView the calendarForTodayView of this CalendarForTodayImplController object.
     *
     */
    public void setCalendarForTodayView(CalendarForTodayView calendarForTodayView) {
        this.calendarForTodayView = calendarForTodayView;
    }

    /**
     * Method doWork realizes view calendar for today.
     *
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(AbstractTaskList list) {
        LocalDateTime endCurrentDay = LocalDateTime.of(LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                23, 59, 59);
        SortedMap<LocalDateTime, Set<Task>> calendar =
                calendar(list, LocalDateTime.now(), endCurrentDay);
        calendarForTodayView.doShow(calendar);
    }
}
