package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.DeleteTaskController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.DeleteTaskView;

/**
 * Class DeleteTaskImplController realizes delete task.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class DeleteTaskControllerImpl implements DeleteTaskController {
    private DeleteTaskView deleteTaskView;

    /**
     * Method setDeleteTaskView sets the deleteTaskView of this DeleteTaskImplController object.
     *
     *
     *
     * @param deleteTaskView the deleteTaskView of this DeleteTaskImplController object.
     *
     */
    public void setDeleteTaskView(DeleteTaskView deleteTaskView) {
        this.deleteTaskView = deleteTaskView;
    }

    /**
     * Method doWork realizes delete task.
     *
     * @param list of type AbstractTaskList
     * @param task of type Task
     */
    @Override
    public void doWork(AbstractTaskList list, Task task) {
        list.remove(task);
        deleteTaskView.doShow();
    }
}
