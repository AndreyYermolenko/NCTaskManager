package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

/**
 * Interface ChangeNameController describes class ChangeNameImplController that realize change task name.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface ChangeNameController {
    /**
     * Method doWork realizes change task name.
     *
     * @param task of type Task
     * @param title of type String
     */
    void doWork(Task task, String title);
}
