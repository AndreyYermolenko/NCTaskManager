package ua.edu.sumdu.j2se.yermolenko.tasks;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.CheckTasksThread;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.add.AddNewTaskImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.AllTasksImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.CalendarForTodayImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.CalendarForWeekImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.NextFiveTasksImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.add.AddNewTaskImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.change.ChangeDeleteTaskImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.MainMenuImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.view.*;

import java.io.*;
import java.util.HashSet;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.readBinary;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.writeBinary;
//24-08-2020 16:00$01:05:04$26-08-2020 16:00
//26-12-2019 11:20$00:00:5$27-12-2019 21:00
//26-12-2019 23:50

public class Main {
    public static HashSet<Integer> uniqueTasksID = new HashSet<>();

    public static void main(String[] args) {
        AbstractTaskList list = new ArrayTaskList();
        MainMenuImplView mainMenu = new MainMenuImplView();

        //1.Посмотреть список задач.
        NextFiveTasksImplController nextFiveTasksImplController = new NextFiveTasksImplController();
        NextFiveTasksImplView nextFiveTasksImplView = new NextFiveTasksImplView();
        nextFiveTasksImplController.setView(nextFiveTasksImplView);
        nextFiveTasksImplView.setController(nextFiveTasksImplController);

        CalendarForTodayImplController calendarForTodayImplController = new CalendarForTodayImplController();
        CalendarForTodayImplView calendarForTodayImplView = new CalendarForTodayImplView();
        calendarForTodayImplController.setView(calendarForTodayImplView);
        calendarForTodayImplView.setController(calendarForTodayImplController);

        CalendarForWeekImplController calendarForWeekImplController = new CalendarForWeekImplController();
        CalendarForWeekImplView calendarForWeekImplView = new CalendarForWeekImplView();
        calendarForWeekImplController.setView(calendarForWeekImplView);
        calendarForWeekImplView.setController(calendarForWeekImplController);

        AllTasksImplController allTasksImplController = new AllTasksImplController();
        AllTasksImplView allTasksImplView = new AllTasksImplView();
        allTasksImplController.setView(allTasksImplView);
        allTasksImplView.setController(allTasksImplController);

        ViewTasksImplView viewTasksImplView = new ViewTasksImplView();
        viewTasksImplView.setView1(nextFiveTasksImplView);
        viewTasksImplView.setView2(calendarForTodayImplView);
        viewTasksImplView.setView3(calendarForWeekImplView);
        viewTasksImplView.setView4(allTasksImplView);


        //2.Добавить новую задачу.
        AddNewTaskImplController addNewTaskImplController = new AddNewTaskImplController();
        AddNewTaskImplView addNewTaskImplView = new AddNewTaskImplView();
        addNewTaskImplController.setView(addNewTaskImplView);
        addNewTaskImplView.setController(addNewTaskImplController);


        //3.Изменить/удалить задачу.
        ChangeDeleteTaskImplView changeDeleteTasks = new ChangeDeleteTaskImplView();


        mainMenu.setViewTasks(viewTasksImplView);
        mainMenu.setAddDeleteTasks(addNewTaskImplView);
        mainMenu.setChangeDeleteTasks(changeDeleteTasks);

        readBinary(list);

        //проверка и уведомление о грядущих задачах
        CheckTasksThread threadDemon = new CheckTasksThread(list);
        threadDemon.setDaemon(true);
        threadDemon.start();

        //считываем все ID в одно статическое множество
        for(Task task: list) {
            uniqueTasksID.add(task.getID());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            mainMenu.doWork(reader, list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeBinary(list);
    }
}


//    String date1 = "24-08-2020 16:00";
//    String date2_st = "01-03-2020 08:15";
//    String date2_end = "01-09-2020 08:15";
//    String date3_st = "20-08-2020 08:15";
//    String date3_end = "28-08-2020 08:15";
//    String date4 = "01-09-2020 18:00";
//
//    AbstractTaskList taskList = new ArrayTaskList();
//        list.add(new Task("Обід із гарною дівчиною", generateUniqueID(), parseDateTime(date1)));
//                list.add(new Task("Ранкова пробіжка", generateUniqueID(),
//                parseDateTime(date2_st), parseDateTime(date2_end), 86400));
//                list.add(new Task("Прийом ліків", generateUniqueID(),
//                parseDateTime(date3_st), parseDateTime(date3_end), 43200));
//                list.add(new Task("Зустріч з друзями", generateUniqueID(),  parseDateTime(date4)));
//                list.forEach(task -> task.setActive(true));

//    public static LocalDateTime parseDateTime(String dateString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//
//        try {
//            return LocalDateTime.parse(dateString, formatter);
//        } catch (DateTimeParseException ex) {
//            System.out.println("Неправильный формат даты/времени.\n"
//                    + "Введите дату/время в формате: dd-MM-yyyy HH:mm");
//            return null;
//        }
//    }


//package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;
//
//import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
//import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
//import ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//
//import static ua.edu.sumdu.j2se.yermolenko.tasks.Main.uniqueTasksID;
//import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.*;
//import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.splitDate;
//import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.writeBinary;
//import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.*;
//
//public class ChangeDeleteTaskController extends AbstractController {
//    public ChangeDeleteTaskController(int menuNumber) {
//        super(menuNumber);
//    }
//    private boolean taskExist;
//
//    @Override
//    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
//        taskExist = true;
//        Task task = null;
//        String inputLine;
//        String rexEx = "[1-4]";
//        int number;
//        int ID = inputID(reader);
//
//        for(Task item: listTasks) {
//            if (item.getID() == ID) {
//                task = item;
//            }
//        }
//
//        while (ID != 0 && taskExist) {
//            printMenuChangeDelete();
//
//            try {
//                inputLine = reader.readLine();
//                if ("".equals(inputLine)) {
//                    writeBinary(listTasks);
//                    break;
//                }
//                if (validateNumberMenu(inputLine, rexEx)) {
//                    number = Integer.parseInt(inputLine);
//                    chooseMainCase(reader, listTasks, task, number);
//                } else {
//                    throw new IllegalArgumentException();
//                }
//            } catch (IllegalArgumentException | DateTimeParseException e) {
//                printErr("WARNING: Сделайте правильный ввод!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, Task task, int number) {
//        switch (number) {
//            case 1:
//                changeName(reader, task);
//                break;
//            case 2:
//                changeActivity(reader, task);
//                break;
//            case 3:
//                changeTime(reader, task);
//                break;
//            case 4:
//                deleteTask(reader, list, task);
//                break;
//        }
//    }
//
//    private void changeName(BufferedReader reader, Task task) {
//        printText("Текущее имя: " + task.getTitle() + "\n"
//                        + "Введите новое имя задачи: "
//                );
//        try {
//            String name = reader.readLine();
//            task.setTitle(name);
//            printText("Имя успешно изменено!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void changeActivity(BufferedReader reader, Task task) {
//        printText("Текущая активность задачи: " + task.isActive() + "\n"
//                + "Измените активность вводом true/false: "
//        );
//        try {
//            String activity = reader.readLine();
//            if(!activity.equals("true") && !activity.equals("false")) {
//                throw new IllegalArgumentException();
//            }
//            task.setActive(Boolean.parseBoolean(activity));
//            printText("Активность задачи успешно изменена!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void changeTime(BufferedReader reader, Task task) {
//        String [] dateTime;
//        try {
//            printMenuSetTime();
//            dateTime = ServiceMethods.parseDateTime(reader.readLine());
//            if (dateTime.length == 1) {
//                LocalDateTime time = parseDateTime(dateTime[0]);
//                task.setTime(time);
//                printText("Время успешно изменено!");
//            } else {
//                LocalDateTime start = parseDateTime(dateTime[0]);
//                int interval = parseInterval(dateTime[1]);
//                LocalDateTime end = parseDateTime(dateTime[2]);
//                task.setTime(start, end, interval);
//                printText("Время успешно изменено!");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void deleteTask(BufferedReader reader, AbstractTaskList list, Task task) {
//        printText("Вы действительно хотите удалить задачу " + task.getTitle() + "?" + "\n"
//                + "Введите true/false: "
//        );
//        try {
//            String confirm = reader.readLine();
//            if(confirm.equals("true") ) {
//                list.remove(task);
//                taskExist = false;
//                printText("Задача успешно удалена!");
//            } else if (confirm.equals("false")) {
//                return;
//            } else {
//                throw new IllegalArgumentException();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private int inputID(BufferedReader reader) {
//        while(true) {
//            printText("Введите ID задачи: трёхзначное целое число." + "\n"
//                    + "Или введите 0 для выхода в главное меню.");
//            try {
//                String line = reader.readLine();
//                if("0".equals(line)) {
//                    return 0;
//                }
//                int ID = parseID(line);
//                if (uniqueTasksID.contains(ID)) {
//                    return ID;
//                } else {
//                    throw new IllegalArgumentException();
//                }
//            } catch (IllegalArgumentException | DateTimeParseException e) {
//                printErr("WARNING: Сделайте правильный ввод!");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
