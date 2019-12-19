package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ServiceMethods.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.*;

public class MainController extends AbstractController {
    private final int VIEW_TASK_LIST = 1;
    private final int ADD_NEW_TASK = 2;
    private final int CHANGE_DELETE_TASK = 3;

    public MainController(int menuNumber) {
        super(menuNumber);
    }

    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
        ArrayList<AbstractController> listControllers = new ArrayList<>();
        createControllers(listControllers);
        int countMenuItems = listControllers.size();
        int number;
        String inputLine;

        while(true) {
            printMainMenu();
            try {
                inputLine = reader.readLine();
                if ("4".equals(inputLine)) {
                    break;
                }
                number = Integer.parseInt(inputLine);
                if (validateNumberMenu(number, countMenuItems)) {
                    chooseMainCase(reader, listTasks, number, listControllers);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                printErr("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createControllers(ArrayList<AbstractController> list) {
        list.add(new ViewTaskListController(VIEW_TASK_LIST));
        list.add(new AddNewTaskController(ADD_NEW_TASK));
        list.add(new ChangeDeleteTaskController(CHANGE_DELETE_TASK));
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList listTasks, int numberCase, ArrayList<AbstractController> listControllers) {
        for(AbstractController controller: listControllers) {
            if (controller.getMenuNumber() == numberCase) {
                controller.doWork(reader, listTasks);
                break;
            }
        }
    }
}
