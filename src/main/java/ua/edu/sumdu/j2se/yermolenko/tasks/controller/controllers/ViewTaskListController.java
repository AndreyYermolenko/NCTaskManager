package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ServiceMethods.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.calendar;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.getNextFiveTasks;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.*;

public class ViewTaskListController extends AbstractController {
    public ViewTaskListController(int menuNumber) {
        super(menuNumber);
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
        printMenuViewTasks();
        String inputLine;
        String rexEx = "[1-4]";
        int number;

        try {
            inputLine = reader.readLine();
            if (validateNumberMenu(inputLine, rexEx)) {
                number = Integer.parseInt(inputLine);
                chooseMainCase(reader, listTasks, number);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, int number) {
        switch (number) {
            case 1:
                viewNextFiveTasks(reader, list);
                break;
            case 2:
                viewCalendarForToday(reader, list);
                break;
            case 3:
                viewCalendarForWeek(reader, list);
                break;
            case 4:
                viewAllTasks(reader, list);
                break;
        }
    }

    private void viewNextFiveTasks(BufferedReader reader, AbstractTaskList list) {
        AbstractTaskList listNextTask = getNextFiveTasks(list);
        for (Task task: listNextTask) {
            printText(task.nextTimeAfter(LocalDateTime.now()) + " "
                    + task.getDescription());
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewCalendarForToday(BufferedReader reader, AbstractTaskList list) {
        LocalDateTime endCurrentDay = LocalDateTime.of(LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                23, 59, 59);
        SortedMap<LocalDateTime, Set<Task>> calendar =
                calendar(list, LocalDateTime.now(), endCurrentDay);

        for (Map.Entry pair: calendar.entrySet()) {
            printText(pair.getKey() + " " + pair.getValue());
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewCalendarForWeek(BufferedReader reader, AbstractTaskList list) {
        SortedMap<LocalDateTime, Set<Task>> calendar =
                calendar(list, LocalDateTime.now(), LocalDateTime.now().plusDays(7));

        for (Map.Entry pair: calendar.entrySet()) {
            printText(pair.getKey() + " " + pair.getValue());
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewAllTasks(BufferedReader reader, AbstractTaskList list) {
        LocalDateTime time;
        list.sort();
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
            e.printStackTrace();
        }
    }
}
