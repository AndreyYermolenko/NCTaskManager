package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.CalendarForWeekController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.CalendarForWeekView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.calendar;

/**
 * Class CalendarForWeekImplController realizes view calendar for week.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class CalendarForWeekControllerImpl implements CalendarForWeekController {
    private CalendarForWeekView calendarForWeekView;

    /**
     * Method setCalendarForWeekView sets the calendarForWeekView of this CalendarForWeekImplController object.
     *
     *
     *
     * @param calendarForWeekView the calendarForWeekView of this CalendarForWeekImplController object.
     *
     */
    public void setCalendarForWeekView(CalendarForWeekView calendarForWeekView) {
        this.calendarForWeekView = calendarForWeekView;
    }

    /**
     * Method doWork realizes view calendar for week.
     *
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(AbstractTaskList list) {
        SortedMap<LocalDateTime, Set<Task>> calendar =
                calendar(list, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        calendarForWeekView.doShow(calendar);
    }
}
