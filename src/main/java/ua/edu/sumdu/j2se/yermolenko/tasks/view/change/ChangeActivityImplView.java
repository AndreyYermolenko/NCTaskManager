package ua.edu.sumdu.j2se.yermolenko.tasks.view.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.parseBoolean;


public class ChangeActivityImplView implements View {
    private BufferedReader reader;
    private Task task;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void doWork(Object... args) {
        this.reader = (BufferedReader) args[0];
        this.task = (Task) args[1];

        printText("Текущая активность задачи: " + task.isActive() + "\n"
                + "Измените активность вводом true/false: "
        );
        try {
            boolean activity = parseBoolean(reader.readLine());
            controller.doWork(task, activity);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doShow(Object o) {
        printText("Активность задачи успешно изменена!");
    }
}
