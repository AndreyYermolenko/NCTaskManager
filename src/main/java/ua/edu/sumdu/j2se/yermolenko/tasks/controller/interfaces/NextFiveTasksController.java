package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

/**
 * Interface NextFiveTasksController describes class NextFiveTasksImplController that realize view next five tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface NextFiveTasksController {
    /**
     * Method doWork realizes view next five tasks.
     *
     * @param list of type AbstractTaskList
     */
    void doWork(AbstractTaskList list);
}
