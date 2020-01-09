package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.ChangeActivityController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeActivityView;

/**
 * Class ChangeActivityImplController realizes change task activity.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeActivityControllerImpl implements ChangeActivityController {
    private ChangeActivityView changeActivityView;

    /**
     * Method setChangeActivityView sets the changeActivityView of this ChangeActivityImplController object.
     *
     *
     *
     * @param changeActivityView the changeActivityView of this ChangeActivityImplController object.
     *
     */
    public void setChangeActivityView(ChangeActivityView changeActivityView) {
        this.changeActivityView = changeActivityView;
    }

    /**
     * Method doWork realize changes task activity
     *
     * @param task of type Task
     * @param activity of type Boolean
     */
    @Override
    public void doWork(Task task, Boolean activity) {
        task.setActive(activity);
        changeActivityView.doShow();
    }
}
