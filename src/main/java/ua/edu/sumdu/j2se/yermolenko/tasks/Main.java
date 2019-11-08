package ua.edu.sumdu.j2se.yermolenko.tasks;

public class Main {

	public static void main(String[] args) {
		Task task = new Task("some", 10, 100, 20);
		task.setActive(true);
		int next = task.nextTimeAfter(85);
		System.out.println(next);
	}
}
