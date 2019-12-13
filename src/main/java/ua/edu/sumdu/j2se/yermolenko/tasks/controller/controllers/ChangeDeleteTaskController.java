package ua.edu.sumdu.j2se.yermolenko.tasks.controller.controllers;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;

public class ChangeDeleteTaskController extends AbstractController {
    public ChangeDeleteTaskController(int menuNumber) {
        super(menuNumber);
    }

    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        System.out.println("ChangeDeleteTaskController");
    }
}
