package ua.edu.sumdu.j2se.yermolenko.tasks.view.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;

public class ChangeDeleteTaskImplView implements View {
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {

    }

    @Override
    public void doShow(Object o) {
        System.out.println(
                "3.1 Изменить название." + "\n"
                        + "3.2 Изменить активность." + "\n"
                        + "3.3 Изменить время." + "\n"
                        + "3.4 Удалить задачу." + "\n"
                        + "Для сохранения изменений и выхода в главное меню нажмите Enter." + "\n"
                        + "Ваш ввод: ");
    }
}
