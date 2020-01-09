package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.NextFiveTasksController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.NextFiveTasksView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

/**
 * Class NextFiveTasksImplView realizes view next five tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class NextFiveTasksViewImpl implements NextFiveTasksView {
    private BufferedReader reader;
    private NextFiveTasksController nextFiveTasksController;
    private final static Logger logger = LogManager.getLogger(NextFiveTasksViewImpl.class);


    /**
     * Method setNextFiveTasksController sets the nextFiveTasksController of this NextFiveTasksImplView object.
     *
     *
     *
     * @param nextFiveTasksController the nextFiveTasksController of this NextFiveTasksImplView object.
     *
     */
    public void setNextFiveTasksController(NextFiveTasksController nextFiveTasksController) {
        this.nextFiveTasksController = nextFiveTasksController;
    }

    /**
     * Method doWork realizes view next five tasks.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        this.reader = reader;
        nextFiveTasksController.doWork(list);
    }

    /**
     * Method doShow shows result.
     *
     * @param list of type AbstractTaskList
     */
    @Override
    public void doShow(AbstractTaskList list) {
        for (Task task: list) {
            printText(task.nextTimeAfter(LocalDateTime.now()) + " "
                    + task.getDescription());
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            logger.error("Problem display next five tasks", e);
        }
    }
}
