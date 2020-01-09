package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.ChangeTimeController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeTimeView;

import java.time.LocalDateTime;

/**
 * Class ChangeTimeImplController realizes change task time.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeTimeControllerImpl implements ChangeTimeController {
    private ChangeTimeView changeTimeView;

    /**
     * Method setChangeTimeView sets the changeTimeView of this ChangeTimeImplController object.
     *
     *
     *
     * @param changeTimeView the changeTimeView of this ChangeTimeImplController object.
     *
     */
    public void setChangeTimeView(ChangeTimeView changeTimeView) {
        this.changeTimeView = changeTimeView;
    }

    /**
     * Method doWork realizes change task time.
     *
     * @param args of type Object: Task task, LocalDateTime start, LocalDateTime end, int interval.
     */
    @Override
    public void doWork(Object... args) {
        Task task = (Task) args[0];

        if (args.length == 2) {
            task.setTime((LocalDateTime) args[1]);
        } else {
            task.setTime((LocalDateTime) args[1],
                    (LocalDateTime) args[2],
                    (int) args[3]);
        }
        changeTimeView.doShow();
    }
}
