package ua.edu.sumdu.j2se.yermolenko.tasks.controller.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.getNextFiveTasks;

public class NextFiveTasksImplController implements Controller {
    View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void doWork(Object...args) {
        AbstractTaskList list = (AbstractTaskList) args[0];
        AbstractTaskList listNextTask = getNextFiveTasks(list);
        view.doShow(listNextTask);
    }
}
