package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public abstract AbstractTaskList createList();
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream();

    public final AbstractTaskList incoming(int from, int to) {
        AbstractTaskList cutList = this.createList();
        this.getStream().filter(task -> task.nextTimeAfter(from) != -1
                && task.nextTimeAfter(from) < to).forEach(cutList::add);
        return cutList;
    }

//    public final AbstractTaskList incoming(int from, int to) {
//        AbstractTaskList cutList = this.createList();
//        Iterator<Task> iterator = this.iterator();
//        while (iterator.hasNext()) {
//            Task task = iterator.next();
//            int incoming = task.nextTimeAfter(from);
//            if (incoming < to && incoming != -1) {
//                cutList.add(task);
//            }
//        }
//        return cutList;
//    }

    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder();
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext()) {
            stringList.append(iterator.next() + "\n");
        }
        return stringList.toString();
    }
}
