package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

import static ua.edu.sumdu.j2se.yermolenko.tasks.controller.ValidateData.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.menu1;

public class ViewTaskListController extends AbstractController {
    public ViewTaskListController(int menuNumber) {
        super(menuNumber);
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList listTasks) {
        menu1();
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
        } catch (IllegalArgumentException e) {
            System.out.println("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, int number) {

    }
}
