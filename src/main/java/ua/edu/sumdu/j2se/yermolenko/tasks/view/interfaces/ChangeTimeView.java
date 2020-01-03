package ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;

import java.io.BufferedReader;

public interface ChangeTimeView {
    void doWork(BufferedReader reader, Task task);
    void doShow();
}
