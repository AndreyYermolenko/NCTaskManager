package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

/**
 * Interface CalendarForTodayController describes class CalendarForTodayImplController that realize view calendar for today.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface CalendarForTodayController {
    /**
     * Method doWork realizes view calendar for today.
     *
     * @param list of type AbstractTaskList
     */
    void doWork(AbstractTaskList list);
}
