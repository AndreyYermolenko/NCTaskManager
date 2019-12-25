package ua.edu.sumdu.j2se.yermolenko.tasks.view.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;

public class ChangeNameImplView implements View {
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

        printText("Текущее имя: " + task.getTitle() + "\n"
                + "Введите новое имя задачи: "
        );
        try {
            String name = reader.readLine();
            controller.doWork(task, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doShow(Object o) {
        printText("Имя успешно изменено!");
    }
}