package ua.edu.sumdu.j2se.yermolenko.tasks.view.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.Controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.parseBoolean;

public class DeleteTaskImplView implements View {
    private BufferedReader reader;
    private Task task;
    private AbstractTaskList list;
    private ChangeDeleteTaskImplView.TaskExist taskExist;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void doWork(Object... args) {
        this.reader = (BufferedReader) args[0];
        this.task = (Task) args[1];
        this.list = (AbstractTaskList) args[2];
        this.taskExist = (ChangeDeleteTaskImplView.TaskExist) args[3];

        printText("Вы действительно хотите удалить задачу " + task.getTitle() + "?" + "\n"
                + "Введите true/false: "
        );
        try {
            Boolean confirm = parseBoolean(reader.readLine());
            if(confirm) {
                controller.doWork(list, task);
                taskExist.setTaskExist(false);
            } else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doShow(Object o) {
        printText("Задача успешно удалена!");
    }
}