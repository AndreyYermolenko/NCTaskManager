package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

/**
 * Interface AllTasksViewController describes class AllTasksImplController realize view all tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface AllTasksViewController {
    /**
     * Method doWork realizes view all tasks.
     *
     * @param list of type AbstractTaskList
     */
    void doWork(AbstractTaskList list);
}
