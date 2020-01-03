package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.ChangeActivityController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeActivityView;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.parseBoolean;


/**
 * Class ChangeActivityImplView realizes change task activity.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeActivityImplView implements ChangeActivityView {
    private ChangeActivityController changeActivityController;
    private final static Logger logger = LogManager.getLogger(ChangeActivityImplView.class);


    /**
     * Method setChangeActivityController sets the changeActivityController of this ChangeActivityImplView object.
     *
     *
     *
     * @param changeActivityController the changeActivityController of this ChangeActivityImplView object.
     *
     */
    public void setChangeActivityController(ChangeActivityController changeActivityController) {
        this.changeActivityController = changeActivityController;
    }

    /**
     * Method doWork realizes change task activity.
     *
     * @param reader of type BufferedReader
     * @param task of type Task
     */
    @Override
    public void doWork(BufferedReader reader, Task task) {
        printText("Текущая активность задачи: " + task.isActive() + "\n"
                + "Измените активность вводом true/false: "
        );
        try {
            boolean activity = parseBoolean(reader.readLine());
            changeActivityController.doWork(task, activity);
        } catch (IOException e) {
            logger.error(e);
        }

    }

    /**
     * Method doShow shows result.
     */
    @Override
    public void doShow() {
        printText("Активность задачи успешно изменена!");
    }
}
