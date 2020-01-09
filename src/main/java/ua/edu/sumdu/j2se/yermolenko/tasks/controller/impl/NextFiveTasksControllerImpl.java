package ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl;

import ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces.NextFiveTasksController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.NextFiveTasksView;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.Tasks.getNextFiveTasks;

/**
 * Class NextFiveTasksImplController realizes view next five tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class NextFiveTasksControllerImpl implements NextFiveTasksController {
    private NextFiveTasksView nextFiveTasksView;

    /**
     * Method setNextFiveTasksView sets the nextFiveTasksView of this NextFiveTasksImplController object.
     *
     *
     *
     * @param nextFiveTasksView the nextFiveTasksView of this NextFiveTasksImplController object.
     *
     */
    public void setNextFiveTasksView(NextFiveTasksView nextFiveTasksView) {
        this.nextFiveTasksView = nextFiveTasksView;
    }

    /**
     * Method doWork realizes view next five tasks.
     *
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(AbstractTaskList list) {
        AbstractTaskList listNextTask = getNextFiveTasks(list);
        nextFiveTasksView.doShow(listNextTask);
    }
}
