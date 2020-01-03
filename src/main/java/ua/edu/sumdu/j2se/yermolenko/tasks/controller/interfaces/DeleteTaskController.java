package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

/**
 * Interface DeleteTaskController describes class DeleteTaskImplController that realize delete task.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface DeleteTaskController {
    /**
     * Method doWork realizes delete task.
     *
     * @param list of type AbstractTaskList
     * @param task of type Task
     */
    void doWork(AbstractTaskList list, Task task);
}
