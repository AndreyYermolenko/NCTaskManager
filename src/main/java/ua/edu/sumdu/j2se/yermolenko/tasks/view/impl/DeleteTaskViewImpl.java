package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.DeleteTaskControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.DeleteTaskView;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.parseBoolean;

/**
 * Class DeleteTaskImplView realizes delete task.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class DeleteTaskViewImpl implements DeleteTaskView {
    private DeleteTaskControllerImpl deleteTaskControllerImpl;
    private final static Logger logger = LogManager.getLogger(DeleteTaskViewImpl.class);


    /**
     * Method setDeleteTaskImplController sets the deleteTaskImplController of this DeleteTaskImplView object.
     *
     *
     *
     * @param deleteTaskControllerImpl the deleteTaskImplController of this DeleteTaskImplView object.
     *
     */
    public void setDeleteTaskControllerImpl(DeleteTaskControllerImpl deleteTaskControllerImpl) {
        this.deleteTaskControllerImpl = deleteTaskControllerImpl;
    }

    /**
     * Method doWork realizes delete task.
     *
     * @param reader of type BufferedReader
     * @param task of type Task
     * @param list of type AbstractTaskList
     * @param taskExist of type TaskExist
     */
    @Override
    public void doWork(BufferedReader reader, Task task, AbstractTaskList list,
                       ChangeDeleteTaskViewImpl.TaskExist taskExist) {

        printText("Вы действительно хотите удалить задачу " + task.getTitle() + "?" + "\n"
                + "Введите true/false: "
        );
        try {
            Boolean confirm = parseBoolean(reader.readLine());
            if(confirm) {
                deleteTaskControllerImpl.doWork(list, task);
                taskExist.setTaskExist(false);
            } else {
                return;
            }
        } catch (IOException e) {
            logger.error("Problem deleting a task", e);
        }
    }

    /**
     * Method doShow shows result.
     */
    @Override
    public void doShow() {
        printText("Задача успешно удалена!");
    }
}