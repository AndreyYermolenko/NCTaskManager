package ua.edu.sumdu.j2se.yermolenko.tasks.controller;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.calendar;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.TextMenu.printErr;

public class CheckTasksThread extends Thread {
    AbstractTaskList list;


    public CheckTasksThread(AbstractTaskList list) {
        this.list = list;
    }

    @Override
    public void run() {
        SortedMap mapNextTasks;
        while (true) {
            mapNextTasks = calendar(list, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
            if (!mapNextTasks.isEmpty()) {
                printErr("***" + "\n"
                        + "УВЕДОМЛЕНИЕ! Скоро должны выполниться следующие задачи: "
                        + mapNextTasks + "\n"
                        + "***");
            }
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
