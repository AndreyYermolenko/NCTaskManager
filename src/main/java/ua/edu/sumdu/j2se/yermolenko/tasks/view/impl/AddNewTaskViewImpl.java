package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.AddNewTaskController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.AddNewTaskView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.*;

/**
 * Class AddNewTaskImplView realizes add new task.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class AddNewTaskViewImpl implements AddNewTaskView {
    private BufferedReader reader;
    private AddNewTaskController addNewTaskController;
    private final static Logger logger = LogManager.getLogger(AddNewTaskViewImpl.class);

    /**
     * Method setAddNewTaskController sets the addNewTaskController of this AddNewTaskImplView object.
     *
     *
     *
     * @param addNewTaskController the addNewTaskController of this AddNewTaskImplView object.
     *
     */
    public void setAddNewTaskController(AddNewTaskController addNewTaskController) {
        this.addNewTaskController = addNewTaskController;
    }

    /**
     * Method doWork realizes add new task.
     *
     * @param args of types BufferedReader, AbstractTaskList, LocalDateTime and int.
     */
    @Override
    public void doWork(Object...args) {
        this.reader = (BufferedReader) args[0];
        AbstractTaskList list = (AbstractTaskList) args[1];
        String title;
        String[] dateTime;
        try {
            printText("Введите название задачи: ");
            title = reader.readLine();
            if ("".equals(title)) {
                throw new IllegalArgumentException();
            }
            printMenuSetTime();
            dateTime = splitDate(reader.readLine());
            if ((dateTime.length == 1)) {
                addNewTaskController.doWork(list,
                        title,
                        parseDateTime(dateTime[0])
                );
            } else {
                addNewTaskController.doWork(list,
                        title,
                        parseDateTime(dateTime[0]),
                        parseDateTime(dateTime[2]),
                        parseInterval(dateTime[1])
                );
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            logger.error("Problem adding a new task", e);        }
    }

    /**
     * Method doShow shows result.
     */
    @Override
    public void doShow() {
        System.out.println("Задача успешно добавлена!");
    }
}
