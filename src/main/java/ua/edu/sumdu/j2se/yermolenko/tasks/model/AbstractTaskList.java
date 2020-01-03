package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import java.io.Serializable;

/**
 * Class Abstract TaskList is the foundation for create lists.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
    private static final long serialVersionUID = 1;
    /**
     * Method add realizes add task for list.
     *
     * @param task of type Task
     */
    public abstract void add(Task task);
    /**
     * Method remove realizes remove task from list.
     *
     * @param task of type Task
     * @return boolean
     */
    public abstract boolean remove(Task task);
    /**
     * Method size return size list.
     * @return int
     */
    public abstract int size();
    /**
     * Method getTask return task for index.
     *
     * @param index of type int
     * @return Task
     */
    public abstract Task getTask(int index);
    /**
     * Method sort realize sorts list.
     */
    public abstract void sort();

    @Override
    public String toString() {
        StringBuilder stringList = new StringBuilder();
        for (Task task : this) {
            stringList.append(task).append("\n");
        }
        return stringList.toString();
    }
}
