package ua.edu.sumdu.j2se.yermolenko.tasks.view.add;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.*;

public class AddNewTaskImplView implements View {
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
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
                controller.doWork(list,
                        title,
                        parseDateTime(dateTime[0])
                );
            } else {
                controller.doWork(list,
                        title,
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
        System.out.println("Задача успешно добавлена!");
    }
}
