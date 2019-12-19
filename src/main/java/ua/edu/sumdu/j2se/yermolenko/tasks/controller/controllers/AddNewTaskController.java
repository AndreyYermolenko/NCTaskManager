package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Main;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ServiceMethods.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.writeBinary;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.generateUniqueID;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.*;

public class AddNewTaskController extends AbstractController {
    public AddNewTaskController(int menuNumber) {
        super(menuNumber);
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        Task newTask;
        String title;
        String[] dateTime;
        try {
            printText("Введите название задачи: ");
            title = reader.readLine();
            if ("".equals(title)) {
                throw new IllegalArgumentException();
            }
            int ID = generateUniqueID();

            printMenuSetTime();
            dateTime = splitDate(reader.readLine());
            if (dateTime.length == 1) {
                LocalDateTime time = parseDateTime(dateTime[0]);
                newTask = new Task(title, ID, time);
                newTask.setActive(true);
                list.add(newTask);
                writeBinary(list, Main.file);
                printText("Задача успешно создана!");
            } else {
                LocalDateTime start = parseDateTime(dateTime[0]);
                int interval = parseInterval(dateTime[1]);
                LocalDateTime end = parseDateTime(dateTime[2]);
                newTask = new Task(title, ID, start, end, interval);
                newTask.setActive(true);
                list.add(newTask);
                writeBinary(list, Main.file);
                printText("Задача успешно создана!");
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
