package ua.edu.sumdu.j2se.yermolenko.tasks.view.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.*;

public class ChangeTimeImplView implements View {
    private BufferedReader reader;
    private Task task;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void doWork(Object... args) {
        this.reader = (BufferedReader) args[0];
        this.task = (Task) args[1];
        String[] dateTime;

        try {
            printMenuSetTime();
            dateTime = splitDate(reader.readLine());
            if ((dateTime.length == 1)) {
                controller.doWork(task,
                        parseDateTime(dateTime[0])
                );
            } else {
                controller.doWork(task,
                        parseDateTime(dateTime[0]),
                        parseDateTime(dateTime[2]),
                        parseInterval(dateTime[1])
                );
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doShow(Object o) {
        printText("Время успешно изменено!");
    }
}