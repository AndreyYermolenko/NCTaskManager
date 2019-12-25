package ua.edu.sumdu.j2se.yermolenko.tasks.view.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;

public class ViewTasksImplView implements View {
    View view1;
    View view2;
    View view3;
    View view4;

    public void setView1(View view1) {
        this.view1 = view1;
    }

    public void setView2(View view2) {
        this.view2 = view2;
    }

    public void setView3(View view3) {
        this.view3 = view3;
    }

    public void setView4(View view4) {
        this.view4 = view4;
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
        doShow(true);
        String inputLine;
        String rexEx = "[1-4]";
        int number;

        try {
            inputLine = reader.readLine();
            if (validateNumberMenu(inputLine, rexEx)) {
                number = Integer.parseInt(inputLine);
                chooseMainCase(reader, listTasks, number);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | IOException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        }
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, int number) {
        switch (number) {
            case 1:
                view1.doWork(reader, list);
                break;
            case 2:
                view2.doWork(reader, list);
                break;
            case 3:
                view3.doWork(reader, list);
                break;
            case 4:
                view4.doWork(reader, list);
                break;
        }
    }

    @Override
    public void doShow(Object o) {
        System.out.println(
                "1.1 Следующие 5 задач." + "\n"
                        + "1.2 Календарь на сегодня." + "\n"
                        + "1.3 Календарь на неделю." + "\n"
                        + "1.4 Все задачи." + "\n"
                        + "Ваш ввод: ");
    }
}
