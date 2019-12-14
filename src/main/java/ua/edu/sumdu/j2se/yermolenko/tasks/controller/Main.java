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
//14-12-2019 19:20$00:00:2$14-12-2020 21:00
//13-12-2019 23:50

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

//        String date1 = "24-08-2020 16:00";
//        String date2_st = "01-03-2020 08:15";
//        String date2_end = "01-09-2020 08:15";
//        String date3_st = "20-08-2020 08:15";
//        String date3_end = "28-08-2020 08:15";
//        String date4 = "01-09-2020 18:00";
//
//        String start = "25-08-2020 08:00";
//        String end = "26-08-2020 08:00";
//
//        AbstractTaskList taskList = new ArrayTaskList();
//        taskList.add(new Task("Обід із гарною дівчиною", parseDateTime(date1)));
//        taskList.add(new Task("Ранкова пробіжка",
//                parseDateTime(date2_st), parseDateTime(date2_end), 86400));
//        taskList.add(new Task("Прийом ліків",
//                parseDateTime(date3_st), parseDateTime(date3_end), 43200));
//        taskList.add(new Task("Зустріч з друзями", parseDateTime(date4)));
//        taskList.forEach(task -> task.setActive(true));
//        //taskList.forEach(System.out::println);
//
//        //AbstractTaskList filterList = (AbstractTaskList) Tasks.incoming(taskList, parseDateTime(start), parseDateTime(end));
//        //filterList.forEach(System.out::println);
//
//        SortedMap map = Tasks.calendar(taskList, parseDateTime(start), parseDateTime(end));
//
//        Set<LocalDateTime> keys = map.keySet();
//        for (LocalDateTime key : keys) {
//            System.out.println(key + " : " + map.get(key));
//        }
//
//
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


//        AbstractTaskList list1 = new ArrayTaskList();
//        AbstractTaskList list2 = new ArrayTaskList();
//
//        for (int i = 10; i < 11; i++) {
//            String date1 = "-08-2020 16:00";
//            list1.add(new Task("someTask" + i, parseDateTime(i + date1)));
//        }
//
//
//        File file = new File("d:/data.txt");
//        FileWriter writer = new FileWriter(file);
//        TaskIO.write(list1, writer);
//        writer.close();
//
//        FileReader reader = new FileReader(file);
//        TaskIO.read(list2, reader);
//        System.out.println("After: \n" + list2);
//        reader.close();


//        File file = new File("d:/data.txt");
//        FileWriter writer = new FileWriter(file);
//        Gson gson = new Gson();
//        String line = gson.toJson(list1);
//        writer.write(line);
//        writer.close();
//
//        FileReader reader = new FileReader(file);
//        BufferedReader br = new BufferedReader(reader);
//        String line2 = br.readLine();
//        ArrayList list = new Gson().fromJson(line2, ArrayList.class);
//        reader.close();
//
//        System.out.println(list);


//        File file = new File("d:/data.txt");
//        FileOutputStream out = new FileOutputStream(file);
//        TaskIO.write(list1, out);
//        out.close();
//
//        FileInputStream in = new FileInputStream(file);
//        TaskIO.read(list2, in);
//        System.out.println("After: \n" + list2);
//        in.close();

//        File file = new File("d:/data.txt");
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
//        out.writeObject(list1);
//        out.close();
//
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
//        list2 = (AbstractTaskList) in.readObject();
//        System.out.println("After: \n" + list2);
//        in.close();
//list1.forEach(System.out::println);

//    public static void main(String[] args) {
//		String date1 = "24-08-2020 16:00";
//		String date2_st = "01-03-2020 08:15";
//		String date2_end = "01-09-2020 08:15";
//		String date3_st = "20-08-2020 08:15";
//		String date3_end = "28-08-2020 08:15";
//		String date4 = "01-09-2020 18:00";
//
//		String start = "25-08-2020 08:00";
//		String end = "26-08-2020 08:00";
//
//		AbstractTaskList taskList = new ArrayTaskList();
//		taskList.add(new Task("Обід із гарною дівчиною", parseDateTime(date1)));
//		taskList.add(new Task("Ранкова пробіжка",
//				parseDateTime(date2_st), parseDateTime(date2_end), 86400));
//		taskList.add(new Task("Прийом ліків",
//				parseDateTime(date3_st), parseDateTime(date3_end), 43200));
//		taskList.add(new Task("Зустріч з друзями", parseDateTime(date4)));
//		taskList.forEach(task -> task.setActive(true));
//		//taskList.forEach(System.out::println);
//
//		//AbstractTaskList filterList = (AbstractTaskList) Tasks.incoming(taskList, parseDateTime(start), parseDateTime(end));
//		//filterList.forEach(System.out::println);
//
//		SortedMap map = Tasks.calendar(taskList, parseDateTime(start), parseDateTime(end));
//
//		Set<LocalDateTime> keys = map.keySet();
//		for (LocalDateTime key: keys) {
//			System.out.println(key + " : " + map.get(key));
//		}
//
//	}





//    String date1 = "24-08-2020 16:00";
//    Task task1 = new Task("name1" , parseDateTime(date1));
//        task1.setActive(true);
//                Task task2;
//
//                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d:/data.txt"));
//                out.writeObject(task1);
//                out.close();
//
//                ObjectInputStream in = new ObjectInputStream(new FileInputStream("d:/data.txt"));
//                task2 = (Task) in.readObject();
//                System.out.println("After: \n" + task2);
//        System.out.println(parseDateTime(date1).getDayOfMonth() + "\n"
//                + parseDateTime(date1).getMonthValue() + "\n"
//                + parseDateTime(date1).getYear() + "\n"
//                + parseDateTime(date1).getHour() + "\n"
//                + parseDateTime(date1).getMinute() + "\n");
//
//                LocalDateTime date2 = LocalDateTime.of(2020, 8, 24, 16, 0);
//                System.out.println(date2);
//        LocalDateTime date = LocalDateTime.now();
//        List<Task> input = Arrays.asList(Task("A", date), Task("B", date), Task("C", date));
//        Iterable<?> res = Tasks.incoming(input, NOW, FROM_NOW_1000);
//        Assert.assertFalse("incoming(" + input + ", " + NOW + ", " + FROM_NOW_1000 + ") має бути пустим, але насправді: " + res, res.iterator().hasNext());
//
//
//
//	    AbstractTaskList list1 = new ArrayTaskList();
//        AbstractTaskList list2 = new LinkedTaskList();
//
//        for (int i = 0; i < 10; i++) {
//            list1.add(new Task("someTask" + i, i));
//            list2.add(new Task("someTask" + i, i));
//        }
//
//        list1.forEach(System.out::println);
//        System.out.println();
//        list2.forEach(System.out::println);
//
//
//		Task task1 = task.clone();
//		AbstractTaskList list = new ArrayTaskList();
//        System.out.println(list.getClass());
//
//        Task task1 = new Task("some1", 10);
//        Task task2 = task1.clone();
//        task2.setTime(15);
//        System.out.println(task1.toString());
//        System.out.println(task2.toString());
//
//
//
//        Task task1 = new Task("some1", 10);
//        Task task2 = new Task("some1", 10);
//        System.out.println(task1.equals(task2));
//
//        task2.setTime(10, 100, 10);
//        task2.setTime(10);
//        System.out.println(task1.equals(task2));
//
//
//
//        Task task2 = new Task("some2", 10, 30, 3);
//
//
//
//        ArrayTaskList listTest = new ArrayTaskList();
//        for (int i = 0; i < 500000; i++) {
//            Task taskTest = new Task("some" + i, i);
//            taskTest.setActive(true);
//            listTest.add(taskTest);
//        }
//
//        long start = System.currentTimeMillis();
//        ArrayTaskList list = listTest.clone();
//        long finish = System.currentTimeMillis();
//        System.out.println(finish - start);
//
//
//        ArrayTaskList listTest = new ArrayTaskList();
//        for (int i = 0; i < 25; i++) {
//            Task taskTest = new Task("some" + i, i, i + 10, 1);
//            taskTest.setActive(true);
//            listTest.add(taskTest);
//        }
//
//        AbstractTaskList listTest1 = listTest.incoming(5, 15);
//        System.out.println(listTest1.toString());
//
//        ArrayTaskList list1 = listTest.clone();
//        //listTest.remove(listTest.getTask(0));
//        listTest.getTask(0).setTime(999);
//        System.out.println(list1);
//        System.out.println(listTest);
//
//        System.out.println(list1.toString());
//        System.out.println(listTest.toString());
//
//
//
//        listTest1.forEach(System.out::println);
//        for(Task l: listTest1) {
//            System.out.println(l);
//        }
//
//        for (int i = 99; i > 5; i--) {
//            listTest.remove(listTest.getTask(i));
//        }
//
//        for(Task t: listTest) {
//            System.out.println(t);
//        }
//
//        AbstractTaskList listTest1 = new ArrayTaskList();
//        for (int i = 0; i < 6; i++) {
//            Task taskTest1 = new Task("some" + i, i);
//            listTest1.add(taskTest1);
//        }
//
//        for(Task t: listTest1) {
//            System.out.println(t);
//        }
//
//        System.out.println(listTest.equals(listTest1));
//
//		task.setActive(true);
//		int next = task.nextTimeAfter(85);
//		System.out.println(next);
//
//		LinkedTaskList linkedTaskList = new LinkedTaskList();
//		linkedTaskList.add(new Task("some1", 10));
//		linkedTaskList.add(new Task("some2", 10));
//		linkedTaskList.add(new Task("some3", 10));
//        linkedTaskList.add(new Task("some4", 10));
//        linkedTaskList.add(new Task("some5", 10));
//		System.out.println(linkedTaskList.size());
////		Task task = linkedTaskList.getTask(4);
////        System.out.println(task);
//
//        System.out.println(linkedTaskList.size());
//        Task task1 = new Task("some1", 10);
//        boolean remove = linkedTaskList.remove(task1);
//        System.out.println(remove);
//        Task task2 = linkedTaskList.getTask(0);
//        Task task3 = linkedTaskList.getTask(1);
//        Task task4 = linkedTaskList.getTask(2);
//        Task task5 = linkedTaskList.getTask(3);
//        System.out.println(task2);
//        System.out.println(task3);
//        System.out.println(task4);
//        System.out.println(task5);
//        System.out.println(linkedTaskList.size());
