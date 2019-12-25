package ua.edu.sumdu.j2se.yermolenko.tasks.controller.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

public class DeleteTaskImplController implements Controller {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void doWork(Object... args) {
        AbstractTaskList list = (AbstractTaskList) args[0];
        Task task = (Task) args[1];
        list.remove(task);
        view.doShow(true);
    }
}
