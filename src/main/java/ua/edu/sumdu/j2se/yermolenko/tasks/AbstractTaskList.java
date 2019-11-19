package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task> {
    public abstract AbstractTaskList createList();
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList cutList = this.createList();
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            int incoming = task.nextTimeAfter(from);
            if (incoming < to && incoming != -1) {
                cutList.add(task);
            }
        }
        return cutList;
    }
}
