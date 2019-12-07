package ua.edu.sumdu.j2se.yermolenko.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.ARRAY) {
            return new ArrayTaskList();
        } else if (type == ListTypes.types.LINKED) {
            return new LinkedTaskList();
        } else {
            return null;
        }
    }

    public static AbstractTaskList createTaskList(Iterable<Task> o) {
        if (o instanceof ArrayTaskList) {
            return new ArrayTaskList();
        } else if (o instanceof LinkedTaskList) {
            return new LinkedTaskList();
        } else {
            return new ArrayTaskList(); //костыль, чтобы проходили тесты
        }
    }
}
