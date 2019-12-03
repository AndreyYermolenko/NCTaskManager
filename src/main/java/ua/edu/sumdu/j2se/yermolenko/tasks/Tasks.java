package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
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
}
