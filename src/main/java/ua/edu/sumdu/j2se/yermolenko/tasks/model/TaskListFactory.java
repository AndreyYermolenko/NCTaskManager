package ua.edu.sumdu.j2se.yermolenko.tasks.model;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(Iterable<Task> o) {
        if (o instanceof ArrayTaskList) {
            return new ArrayTaskList();
        } else if (o instanceof LinkedTaskList) {
            return new LinkedTaskList();
        } else {
            return null;
        }
    }
}
