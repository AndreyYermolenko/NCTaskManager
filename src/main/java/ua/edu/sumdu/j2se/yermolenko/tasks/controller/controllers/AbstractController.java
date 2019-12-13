package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import java.io.BufferedReader;

public abstract class AbstractController {
    private final int MENU_NUMBER;

    public abstract void doWork(BufferedReader reader, AbstractTaskList list);
    public int getMenuNumber() {
        return MENU_NUMBER;
    }
    public AbstractController(int menuNumber) {
        MENU_NUMBER = menuNumber;
    }
}
