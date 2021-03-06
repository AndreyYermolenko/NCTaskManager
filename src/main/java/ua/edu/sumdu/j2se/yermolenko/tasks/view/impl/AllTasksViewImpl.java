package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.AllTasksControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.AllTasksView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

/**
 * Class AllTasksImplView realizes view all tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class AllTasksViewImpl implements AllTasksView {
    private BufferedReader reader;
    private AllTasksControllerImpl allTasksControllerImpl;
    private final static Logger logger = LogManager.getLogger(AllTasksViewImpl.class);

    /**
     * Method setAllTasksImplController sets the allTasksImplController of this AllTasksImplView object.
     *
     *
     *
     * @param allTasksControllerImpl the allTasksImplController of this AllTasksImplView object.
     *
     */
    public void setAllTasksControllerImpl(AllTasksControllerImpl allTasksControllerImpl) {
        this.allTasksControllerImpl = allTasksControllerImpl;
    }

    /**
     * Method doWork realizes view all tasks.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        this.reader = reader;
        allTasksControllerImpl.doWork(list);
    }

    /**
     * Method doShow shows result.
     *
     * @param list of type AbstractTaskList
     */
    @Override
    public void doShow(AbstractTaskList list) {
        LocalDateTime time;
        for (Task task: list) {
            time = task.nextTimeAfter(LocalDateTime.now());
            if (time != null) {
                printText(time.toString() + " "
                        + task.getDescription());
            } else {
                printText("НЕ ВЫПОЛНЯЕТСЯ "
                        + task.toString());
            }
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            logger.error("Problem display all tasks", e);        }
    }
}
