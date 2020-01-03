package ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.io.BufferedReader;

public interface ViewTasksView {
    void doWork(BufferedReader reader, AbstractTaskList list);
    void doShow();
}
