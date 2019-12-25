package ua.edu.sumdu.j2se.yermolenko.tasks.controller.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

public class ChangeNameImplController implements Controller {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void doWork(Object... args) {
        Task task = (Task) args[0];
        task.setTitle((String) args[1]);
        view.doShow(true);
    }
}
