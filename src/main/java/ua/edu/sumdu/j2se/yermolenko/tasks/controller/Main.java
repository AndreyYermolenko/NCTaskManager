package ua.edu.sumdu.j2se.yermolenko.tasks.controller;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers.AbstractController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers.MainController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.*;
import java.util.HashSet;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.readBinary;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.writeBinary;
//24-08-2020 16:00$01:05:04$26-08-2020 16:00
//15-12-2019 11:20$00:00:5$14-12-2020 21:00
//18-12-2019 23:50

public class Main {
    private static int MAIN_MENU = 0;
    public static File file = new File("src" + File.separator
            + "main" + File.separator
            + "resources" + File.separator
            + "data.txt");
    public static HashSet<Integer> uniqueTasksID = new HashSet<>();

    public static void main(String[] args) {

        AbstractTaskList list = new ArrayTaskList();
        AbstractController mainController = new MainController(MAIN_MENU);

        if (file.exists()) {
            readBinary(list, file);
        }

        //проверка и уведомление о грядущих задачах
        CheckTasksThread threadDemon = new CheckTasksThread(list);
        threadDemon.setDaemon(true);
        threadDemon.start();

        //считываем все ID в одно статическое множество
        for(Task task: list) {
            uniqueTasksID.add(task.getID());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            mainController.doWork(reader, list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeBinary(list, file);
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
