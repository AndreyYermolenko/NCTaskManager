package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.AddNewTaskController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.AddNewTaskView;

import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.serialization;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.generateUniqueID;


/**
 * Class AddNewTaskImplController realizes add new task.
 *
 * @author AndreyYermolenko
 * Created on 02.01.2020
 */
public class AddNewTaskControllerImpl implements AddNewTaskController {
    private AddNewTaskView addNewTaskView;

    /**
     * Method setAddNewTaskView sets the addNewTaskView of this AddNewTaskImplController object.
     *
     *
     *
     * @param addNewTaskView the addNewTaskView of this AddNewTaskImplController object.
     *
     */
    public void setAddNewTaskView(AddNewTaskView addNewTaskView) {
        this.addNewTaskView = addNewTaskView;
    }

    /**
     * Method doWork realizes add new task.
     *
     * @param args of types AbstractTaskList, Task, LocalDateTime and int.
     */
    @Override
    public void doWork(Object... args) {
        AbstractTaskList list = (AbstractTaskList) args[0];
        Task newTask;

        int ID = generateUniqueID();
        if (args.length == 3) {
            newTask = new Task(
                    (String) args[1],
                    ID,
                    (LocalDateTime) args[2],
                    true);
        } else {
            newTask = new Task(
                    (String) args[1],
                    ID,
                    (LocalDateTime) args[2],
                    (LocalDateTime) args[3],
                    (int) args[4],
                    true);
        }
        list.add(newTask);
        serialization(list);
        addNewTaskView.doShow();
    }
}
