package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

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
        

//		Task task1 = task.clone();
//		AbstractTaskList list = new ArrayTaskList();
//        System.out.println(list.getClass());

//        Task task1 = new Task("some1", 10);
//        Task task2 = task1.clone();
//        task2.setTime(15);
//        System.out.println(task1.toString());
//        System.out.println(task2.toString());



//        Task task1 = new Task("some1", 10);
//        Task task2 = new Task("some1", 10);
//        System.out.println(task1.equals(task2));
//
//        task2.setTime(10, 100, 10);
//        task2.setTime(10);
//        System.out.println(task1.equals(task2));



//        Task task2 = new Task("some2", 10, 30, 3);



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


        ArrayTaskList listTest = new ArrayTaskList();
        for (int i = 0; i < 25; i++) {
            Task taskTest = new Task("some" + i, i, i + 10, 1);
            taskTest.setActive(true);
            listTest.add(taskTest);
        }

        AbstractTaskList listTest1 = listTest.incoming(5, 15);
        System.out.println(listTest1.toString());

//        ArrayTaskList list1 = listTest.clone();
//        //listTest.remove(listTest.getTask(0));
//        listTest.getTask(0).setTime(999);
//        System.out.println(list1);
//        System.out.println(listTest);

//        System.out.println(list1.toString());
//        System.out.println(listTest.toString());
//


//        listTest1.forEach(System.out::println);
//        for(Task l: listTest1) {
//            System.out.println(l);
//        }

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

//		task.setActive(true);
//		int next = task.nextTimeAfter(85);
//		System.out.println(next);

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
	}
}
