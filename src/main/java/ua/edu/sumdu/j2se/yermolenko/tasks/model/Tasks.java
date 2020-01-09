package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

import static ua.edu.sumdu.j2se.yermolenko.tasks.Main.uniqueTasksID;

/**
 * Class Tasks realizes service methods for Task.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class Tasks {

    /**
     * The method returns a list of tasks that belong to a given period of time.
     *
     * @param tasks of type Iterable<Task>
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @return Iterable
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        AbstractTaskList filterList = TaskListFactory.createTaskList(tasks);
        for(Task task: tasks) {
            if (task.nextTimeAfter(start) != null
                    && task.nextTimeAfter(start).compareTo(end) <= 0) {
                filterList.add(task);
            }
        }
        return filterList;
    }

    /**
     * The method returns a Map collection,
     * which contains all the time points with tasks in a given time period.
     * @param tasks of type Iterable<Task>
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @return SortedMap
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<LocalDateTime, Set<Task>>();
        Iterable<Task> filterList = incoming(tasks, start, end);

        for (Task task: filterList) {
            LocalDateTime time = task.nextTimeAfter(start);
            while (time != null && end.compareTo(time) >= 0) {
                addTaskToMap(map, time, task);
                time = task.nextTimeAfter(time); //следующий момент времени !=, но > передаваемого значения
            }
        }
        return map;
    }

    private static void addTaskToMap(SortedMap<LocalDateTime, Set<Task>> map, LocalDateTime time, Task task) {
        if (!map.containsKey(time)) {
            Set<Task> set = new HashSet<Task>();
            set.add(task);
            map.put(time, set);
        } else {
            map.get(time).add(task); //получили Set и добавили в него task
        }
    }

    /**
     * The method returns a list of 5 tasks that should be completed soon.
     * @param list of type AbstractTaskList
     * @return AbstractTaskList
     */
    public static AbstractTaskList getNextFiveTasks(AbstractTaskList list) {
        AbstractTaskList listNextTasks = TaskListFactory.createTaskList(list);
        list.sort();
        int countTasks = 0;
        for (Task task: list) {
            if (task.nextTimeAfter(LocalDateTime.now()) != null) {
                listNextTasks.add(task);
                countTasks++;
            }
            if (countTasks == 5) {
                break;
            }
        }
        return listNextTasks;
    }

    /**
     * The method generates and returns a unique ID for tasks.
     * @return int
     */
    public static int generateUniqueID() {
        int ID;
        int min = 100;
        int max = 999;
        int diff = max - min;
        Random random = new Random();
        while (true) {
            ID = random.nextInt(diff) + min;
            if (!uniqueTasksID.contains(ID)) {
                uniqueTasksID.add(ID);
                return ID;
            }
        }
    }
}
