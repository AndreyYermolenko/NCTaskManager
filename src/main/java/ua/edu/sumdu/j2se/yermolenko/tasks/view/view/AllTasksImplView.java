package ua.edu.sumdu.j2se.yermolenko.tasks.view.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

public class AllTasksImplView implements View {
    BufferedReader reader;
    Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        this.reader = reader;
        controller.doWork(list);
    }

    @Override
    public void doShow(Object o) {
        AbstractTaskList list = (AbstractTaskList) o;
        LocalDateTime time;
        for (Task task: list) {
            time = task.nextTimeAfter(LocalDateTime.now());
            if (time != null) {
                printText(time.toString() + " "
                        + task.getDescription());
            } else {
                printText("НЕ ВЫПОЛНЯЕТСЯ "
                        + task.toString());
            }
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
