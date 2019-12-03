package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
    private static final long serialVersionUID = 1;

    public abstract AbstractTaskList createList();
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream();

    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder();
        for (Task task : this) {
            stringList.append(task).append("\n");
        }
        return stringList.toString();
    }
}
