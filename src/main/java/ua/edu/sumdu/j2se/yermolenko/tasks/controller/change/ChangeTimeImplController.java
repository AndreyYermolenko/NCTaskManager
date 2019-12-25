package ua.edu.sumdu.j2se.yermolenko.tasks.controller.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.time.LocalDateTime;

public class ChangeTimeImplController implements Controller {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void doWork(Object... args) {
        Task task = (Task) args[0];

        if (args.length == 2) {
            task.setTime((LocalDateTime) args[1]);
        } else {
            task.setTime((LocalDateTime) args[1],
                    (LocalDateTime) args[2],
                    (int) args[3]);
        }
        view.doShow(true);
    }
}
