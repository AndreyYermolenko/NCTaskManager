package ua.edu.sumdu.j2se.yermolenko.tasks.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;

public class MainMenuImplView implements View {
    View viewTasks;
    View addDeleteTasks;
    View changeDeleteTasks;

    public void setViewTasks(View viewTasks) {
        this.viewTasks = viewTasks;
    }

    public void setAddDeleteTasks(View addDeleteTasks) {
        this.addDeleteTasks = addDeleteTasks;
    }

    public void setChangeDeleteTasks(View changeDeleteTasks) {
        this.changeDeleteTasks = changeDeleteTasks;
    }

    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
        int number;
        String inputLine;
        String regEx = "[1-3]";

        while(true) {
            doShow(true);
            try {
                inputLine = reader.readLine();
                if ("4".equals(inputLine)) {
                    break;
                }
                if (validateNumberMenu(inputLine, regEx)) {
                    number = Integer.parseInt(inputLine);
                    chooseMainCase(reader, listTasks, number);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                printErr("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                printErr(e.getMessage());
            }
        }
    }

    @Override
    public void doShow(Object o) {
        System.out.println("Главное меню." + "\n"
                + "1.Посмотреть список задач." + "\n"
                + "2.Добавить новую задачу." + "\n"
                + "3.Изменить/удалить задачу." + "\n"
                + "4.Завершение работы." + "\n"
                + "Ваш ввод: ");
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, int number) {
        switch (number) {
            case 1:
                viewTasks.doWork(reader, list);
                break;
            case 2:
                addDeleteTasks.doWork(reader, list);
                break;
            case 3:
                changeDeleteTasks.doWork(reader, list);
                break;
        }
    }


}
