package ua.edu.sumdu.j2se.yermolenko.tasks.controller.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.calendar;

public class CalendarForWeekImplController implements Controller {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void doWork(Object...args) {
        AbstractTaskList list = (AbstractTaskList) args[0];
        SortedMap<LocalDateTime, Set<Task>> calendar =
                calendar(list, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        view.doShow(calendar);
    }
}
