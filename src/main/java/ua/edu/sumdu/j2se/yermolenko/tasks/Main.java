package ua.edu.sumdu.j2se.yermolenko.tasks;

public class Main {

	public static void main(String[] args) {
//		Task task = new Task("some", 10);
//		Task task1 = task.clone();
//		AbstractTaskList list = new ArrayTaskList();
//        System.out.println(list.getClass());


        AbstractTaskList listTest = new ArrayTaskList();
        for (int i = 0; i < 100; i++) {
            Task taskTest = new Task("some" + i, i);
            taskTest.setActive(true);
            listTest.add(taskTest);
        }
//
        AbstractTaskList listTest1 = listTest.incoming(90, 105);

        listTest1.forEach(System.out::println);
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
