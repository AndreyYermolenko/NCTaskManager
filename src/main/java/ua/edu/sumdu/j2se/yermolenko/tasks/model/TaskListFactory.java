package ua.edu.sumdu.j2se.yermolenko.tasks.model;

/**
 * Class TaskListFactory realizes pattern factory.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class TaskListFactory {
    /**
     * The method returns a new instance of the collection
     * of the same type as the input parameter.
     * @param o of type Iterable<Task>.
     * @return
     */
    public static AbstractTaskList createTaskList(Iterable<Task> o) {
        if (o instanceof ArrayTaskList) {
            return new ArrayTaskList();
        } else if (o instanceof LinkedTaskList) {
            return new LinkedTaskList();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
