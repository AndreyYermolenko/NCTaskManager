package ua.edu.sumdu.j2se.yermolenko.tasks.controller.add;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.writeBinary;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.generateUniqueID;

public class AddNewTaskImplController implements Controller {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void doWork(Object... args) {
        AbstractTaskList list = (AbstractTaskList) args[0];
        Task newTask;

        int ID = generateUniqueID();
        if (args.length == 3) {
            newTask = new Task(
                    (String) args[1],
                    ID,
                    (LocalDateTime) args[2],
                    true);
        } else {
            newTask = new Task(
                    (String) args[1],
                    ID,
                    (LocalDateTime) args[2],
                    (LocalDateTime) args[3],
                    (int) args[4],
                    true);
        }
        list.add(newTask);
        writeBinary(list);
        view.doShow(true);
    }
}
