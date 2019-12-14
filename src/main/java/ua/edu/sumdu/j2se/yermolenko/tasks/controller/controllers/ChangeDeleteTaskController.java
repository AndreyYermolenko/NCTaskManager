package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Main;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.Main.uniqueTasksID;
import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ServiceMethods.*;
import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ServiceMethods.parseDateTime;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.writeBinary;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.printMenuChangeDelete;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.printMenuSetTime;

public class ChangeDeleteTaskController extends AbstractController {
    public ChangeDeleteTaskController(int menuNumber) {
        super(menuNumber);
    }
    private boolean taskExist;

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
        taskExist = true;
        Task task = null;
        String inputLine;
        String rexEx = "[1-4]";
        int number;
        int ID = inputID(reader);

        for(Task item: listTasks) {
            if (item.getID() == ID) {
                task = item;
            }
        }

        while (ID != 0 && taskExist) {
            printMenuChangeDelete();

            try {
                inputLine = reader.readLine();
                if ("".equals(inputLine)) {
                    writeBinary(listTasks, Main.file);
                    break;
                }
                if (validateNumberMenu(inputLine, rexEx)) {
                    number = Integer.parseInt(inputLine);
                    chooseMainCase(reader, listTasks, task, number);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int inputID(BufferedReader reader) {
        while(true) {
            System.out.println("Введите ID задачи: трёхзначное целое число." + "\n"
                    + "Или введите 0 для выхода в главное меню.");
            try {
                String line = reader.readLine();
                if("0".equals(line)) {
                    return 0;
                }
                int ID = parseID(line);
                if (uniqueTasksID.contains(ID)) {
                    return ID;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, Task task, int number) {
        switch (number) {
            case 1:
                changeName(reader, task);
                break;
            case 2:
                changeActivity(reader, task);
                break;
            case 3:
                changeTime(reader, task);
                break;
            case 4:
                deleteTask(reader, list, task);
                break;
        }
    }

    private void changeName(BufferedReader reader, Task task) {
        System.out.println("Текущее имя: " + task.getTitle() + "\n"
                        + "Введите новое имя задачи: "
                );
        try {
            String name = reader.readLine();
            task.setTitle(name);
            System.out.println("Имя успешно изменено!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeActivity(BufferedReader reader, Task task) {
        System.out.println("Текущая активность задачи: " + task.isActive() + "\n"
                + "Измените активность вводом true/false: "
        );
        try {
            String activity = reader.readLine();
            if(!activity.equals("true") && !activity.equals("false")) {
                throw new IllegalArgumentException();
            }
            task.setActive(Boolean.parseBoolean(activity));
            System.out.println("Активность задачи успешно изменена!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeTime(BufferedReader reader, Task task) {
        String [] dateTime;
        try {
            printMenuSetTime();
            dateTime = splitDate(reader.readLine());
            if (dateTime.length == 1) {
                LocalDateTime time = parseDateTime(dateTime[0]);
                task.setTime(time);
                System.out.println("Время успешно изменено!");
            } else {
                LocalDateTime start = parseDateTime(dateTime[0]);
                int interval = parseInterval(dateTime[1]);
                LocalDateTime end = parseDateTime(dateTime[2]);
                task.setTime(start, end, interval);
                System.out.println("Время успешно изменено!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTask(BufferedReader reader, AbstractTaskList list, Task task) {
        System.out.println("Вы действительно хотите удалить задачу " + task.getTitle() + "?" + "\n"
                + "Введите true/false: "
        );
        try {
            String confirm = reader.readLine();
            if(confirm.equals("true") ) {
                list.remove(task);
                taskExist = false;
                System.out.println("Задача успешно удалена!");
            } else if (confirm.equals("false")) {
                return;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
