package ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeDeleteTaskViewImpl;

import java.io.BufferedReader;

public interface DeleteTaskView {
    void doWork(BufferedReader reader, Task task, AbstractTaskList list,
                ChangeDeleteTaskViewImpl.TaskExist taskExist);
    void doShow();
}
