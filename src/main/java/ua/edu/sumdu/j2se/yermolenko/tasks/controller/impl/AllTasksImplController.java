package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.AllTasksViewController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.AllTasksView;

/**
 * Class AllTasksImplController realizes view all tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class AllTasksImplController implements AllTasksViewController {
    private AllTasksView allTasksView;

    /**
     * Method setAllTasksView sets the allTasksView of this AllTasksImplController object.
     *
     *
     *
     * @param allTasksView the allTasksView of this AllTasksImplController object.
     *
     */
    public void setAllTasksView(AllTasksView allTasksView) {
        this.allTasksView = allTasksView;
    }

    /**
     * Method doWork realizes view all tasks.
     *
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(AbstractTaskList list) {
        list.sort();
        allTasksView.doShow(list);
    }
}
