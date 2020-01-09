package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.ChangeTimeControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeTimeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.*;

/**
 * Class ChangeTimeImplView realizes change task time.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeTimeViewImpl implements ChangeTimeView {
    private ChangeTimeControllerImpl changeTimeControllerImpl;
    private final static Logger logger = LogManager.getLogger(ChangeTimeViewImpl.class);

    /**
     * Method setChangeTimeImplController sets the changeTimeImplController of this ChangeTimeImplView object.
     *
     *
     *
     * @param changeTimeControllerImpl the changeTimeImplController of this ChangeTimeImplView object.
     *
     */
    public void setChangeTimeControllerImpl(ChangeTimeControllerImpl changeTimeControllerImpl) {
        this.changeTimeControllerImpl = changeTimeControllerImpl;
    }

    /**
     * Method doWork realizes change task time.
     *
     * @param reader of type BufferedReader
     * @param task of type Task
     */
    @Override
    public void doWork(BufferedReader reader, Task task) {
        String[] dateTime;
        try {
            printMenuSetTime();
            dateTime = splitDate(reader.readLine());
            if ((dateTime.length == 1)) {
                changeTimeControllerImpl.doWork(task,
                        parseDateTime(dateTime[0])
                );
            } else {
                changeTimeControllerImpl.doWork(task,
                        parseDateTime(dateTime[0]),
                        parseDateTime(dateTime[2]),
                        parseInterval(dateTime[1])
                );
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            logger.error("The problem of changing the time of the task", e);
        }
    }

    /**
     * Method doShow show result.
     */
    @Override
    public void doShow() {
        printText("Время успешно изменено!");
    }
}