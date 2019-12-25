package ua.edu.sumdu.j2se.yermolenko.tasks.view;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;

public interface View {
    void doWork(BufferedReader reader, AbstractTaskList list);
    void doShow(Object o);
}
