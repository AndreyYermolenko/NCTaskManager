package ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface CalendarForTodayView {
    void doWork(BufferedReader reader, AbstractTaskList list);
    void doShow(Map<LocalDateTime, Set<Task>> calendar);
}
