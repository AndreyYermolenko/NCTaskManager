package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

/**
 * Interface CalendarForWeekController describes class CalendarForWeekImplController that realize view calendar for week.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface CalendarForWeekController {
    /**
     * Method doWork realizes view calendar for week.
     *
     * @param list of type AbstractTaskList
     */
    void doWork(AbstractTaskList list);
}
