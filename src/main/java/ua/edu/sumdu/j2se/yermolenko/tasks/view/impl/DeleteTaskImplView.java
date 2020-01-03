package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.DeleteTaskImplController;
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
public class DeleteTaskImplView implements DeleteTaskView {
    private DeleteTaskImplController deleteTaskImplController;
    private final static Logger logger = LogManager.getLogger(DeleteTaskImplView.class);


    /**
     * Method setDeleteTaskImplController sets the deleteTaskImplController of this DeleteTaskImplView object.
     *
     *
     *
     * @param deleteTaskImplController the deleteTaskImplController of this DeleteTaskImplView object.
     *
     */
    public void setDeleteTaskImplController(DeleteTaskImplController deleteTaskImplController) {
        this.deleteTaskImplController = deleteTaskImplController;
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
                       ChangeDeleteTaskImplView.TaskExist taskExist) {

        printText("Вы действительно хотите удалить задачу " + task.getTitle() + "?" + "\n"
                + "Введите true/false: "
        );
        try {
            Boolean confirm = parseBoolean(reader.readLine());
            if(confirm) {
                deleteTaskImplController.doWork(list, task);
                taskExist.setTaskExist(false);
            } else {
                return;
            }
        } catch (IOException e) {
            logger.error(e);
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