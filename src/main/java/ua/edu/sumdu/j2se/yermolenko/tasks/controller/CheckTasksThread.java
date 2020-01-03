package ua.edu.sumdu.j2se.yermolenko.tasks.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.calendar;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;

/**
 * Class CheckTasksThread checks next tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class CheckTasksThread extends Thread {
    private AbstractTaskList list;
    private final static Logger logger = LogManager.getLogger(CheckTasksThread.class);

    /**
     * Constructor CheckTasksThread creates a new CheckTasksThread instance.
     *
     * @param list of type AbstractTaskList
     */
    public CheckTasksThread(AbstractTaskList list) {
        this.list = list;
    }

    /**
     * Method run starts check tasks.
     */
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
                logger.error(e);
            }
        }
    }
}
