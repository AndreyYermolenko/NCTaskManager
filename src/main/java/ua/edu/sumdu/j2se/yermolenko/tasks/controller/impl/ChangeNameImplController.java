package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.ChangeNameController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeNameView;

/**
 * Class ChangeNameImplController realizes change task name.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeNameImplController implements ChangeNameController {
    private ChangeNameView changeNameView;

    /**
     * Method setChangeNameView sets the changeNameView of this ChangeNameImplController object.
     *
     *
     *
     * @param changeNameView the changeNameView of this ChangeNameImplController object.
     *
     */
    public void setChangeNameView(ChangeNameView changeNameView) {
        this.changeNameView = changeNameView;
    }

    /**
     * Method doWork realizes change task name.
     *
     * @param task of type Task
     * @param title of type String
     */
    @Override
    public void doWork(Task task, String title) {
        task.setTitle(title);
        changeNameView.doShow();
    }
}
