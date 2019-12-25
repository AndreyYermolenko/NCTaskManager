package ua.edu.sumdu.j2se.yermolenko.tasks;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.CheckTasksThread;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.add.AddNewTaskImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.change.ChangeActivityImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.change.ChangeNameImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.change.ChangeTimeImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.change.DeleteTaskImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.AllTasksImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.CalendarForTodayImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.CalendarForWeekImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.view.NextFiveTasksImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.add.AddNewTaskImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.change.*;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.MainMenuImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.view.*;

import java.io.*;
import java.util.HashSet;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.deSerialization;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.serialization;
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
        ChangeNameImplController changeNameImplController = new ChangeNameImplController();
        ChangeNameImplView changeNameImplView = new ChangeNameImplView();
        changeNameImplController.setView(changeNameImplView);
        changeNameImplView.setController(changeNameImplController);

        ChangeActivityImplController changeActivityImplController = new ChangeActivityImplController();
        ChangeActivityImplView changeActivityImplView = new ChangeActivityImplView();
        changeActivityImplController.setView(changeActivityImplView);
        changeActivityImplView.setController(changeActivityImplController);

        ChangeTimeImplController changeTimeImplController = new ChangeTimeImplController();
        ChangeTimeImplView changeTimeImplView = new ChangeTimeImplView();
        changeTimeImplController.setView(changeTimeImplView);
        changeTimeImplView.setController(changeTimeImplController);

        DeleteTaskImplController deleteTaskImplController = new DeleteTaskImplController();
        DeleteTaskImplView deleteTaskImplView = new DeleteTaskImplView();
        deleteTaskImplController.setView(deleteTaskImplView);
        deleteTaskImplView.setController(deleteTaskImplController);

        ChangeDeleteTaskImplView changeDeleteTaskImplView = new ChangeDeleteTaskImplView();
        changeDeleteTaskImplView.setView1(changeNameImplView);
        changeDeleteTaskImplView.setView2(changeActivityImplView);
        changeDeleteTaskImplView.setView3(changeTimeImplView);
        changeDeleteTaskImplView.setView4(deleteTaskImplView);


        //Главное меню
        mainMenu.setViewTasks(viewTasksImplView);
        mainMenu.setAddDeleteTasks(addNewTaskImplView);
        mainMenu.setChangeDeleteTasks(changeDeleteTaskImplView);

        deSerialization(list);

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

        serialization(list);
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

