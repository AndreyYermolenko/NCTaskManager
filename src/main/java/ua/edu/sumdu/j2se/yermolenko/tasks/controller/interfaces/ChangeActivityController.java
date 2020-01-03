package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

/**
 * Interface ChangeActivityController describes class ChangeActivityImplController that realize change task activity.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface ChangeActivityController {
    /**
     * Method doWork realizes change task activity.
     *
     * @param task of type Task
     * @param activity of type Boolean
     */
    void doWork(Task task, Boolean activity);
}
