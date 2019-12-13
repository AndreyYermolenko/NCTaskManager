package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ValidateData.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.menuSetTime;

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
            System.out.println("Введите название задачи: ");
            title = reader.readLine();
            if ("".equals(title)) {
                throw new IllegalArgumentException();
            }

            menuSetTime();
            dateTime = splitDate(reader.readLine());
            if (dateTime.length == 1) {
                LocalDateTime time = parseDateTime(dateTime[0]);
                newTask = new Task(title, time);
                newTask.setActive(true);
                list.add(newTask);
                System.out.println("Задача успешно создана!");
            } else {
                LocalDateTime start = parseDateTime(dateTime[0]);
                int interval = Integer.parseInt(dateTime[1]);
                LocalDateTime end = parseDateTime(dateTime[2]);
                newTask = new Task(title, start, end, interval);
                newTask.setActive(true);
                list.add(newTask);
                System.out.println("Задача успешно создана!");
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
