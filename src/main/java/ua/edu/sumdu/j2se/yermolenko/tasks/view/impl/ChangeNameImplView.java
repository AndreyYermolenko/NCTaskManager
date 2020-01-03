package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.ChangeNameImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeNameView;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

/**
 * Class ChangeNameImplView realizes change task name.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeNameImplView implements ChangeNameView {
    private ChangeNameImplController changeNameImplController;
    private final static Logger logger = LogManager.getLogger(ChangeNameImplView.class);


    /**
     * Method setChangeNameImplController sets the changeNameImplController of this ChangeNameImplView object.
     *
     *
     *
     * @param changeNameImplController the changeNameImplController of this ChangeNameImplView object.
     *
     */
    public void setChangeNameImplController(ChangeNameImplController changeNameImplController) {
        this.changeNameImplController = changeNameImplController;
    }

    /**
     * Method doWork realizes change task name.
     *
     * @param reader of type BufferedReader
     * @param task of type Task
     */
    @Override
    public void doWork(BufferedReader reader, Task task) {

        printText("Текущее имя: " + task.getTitle() + "\n"
                + "Введите новое имя задачи: "
        );
        try {
            String name = reader.readLine();
            changeNameImplController.doWork(task, name);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * Method doShow shows result.
     */
    @Override
    public void doShow() {
        printText("Имя успешно изменено!");
    }
}