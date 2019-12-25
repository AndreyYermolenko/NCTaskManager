package ua.edu.sumdu.j2se.yermolenko.tasks.view.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

public class NextFiveTasksImplView implements View {
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
        AbstractTaskList listNextTask = (AbstractTaskList) o;
        for (Task task: listNextTask) {
            printText(task.nextTimeAfter(LocalDateTime.now()) + " "
                    + task.getDescription());
        }
        printText("Для продолжения нажмите любую клавишу.");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
