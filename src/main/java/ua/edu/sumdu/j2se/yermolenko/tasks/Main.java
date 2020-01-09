package ua.edu.sumdu.j2se.yermolenko.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.CheckTasksThread;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.*;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.AllTasksControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.CalendarForTodayControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.CalendarForWeekControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.NextFiveTasksControllerImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.*;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeActivityViewImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeNameViewImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeTimeViewImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.DeleteTaskViewImpl;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.MainMenuViewImpl;

import java.io.*;
import java.util.HashSet;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.deSerialization;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.serialization;

public class Main {
    public static HashSet<Integer> uniqueTasksID = new HashSet<>();
    private final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        AbstractTaskList list = new ArrayTaskList();
        MainMenuViewImpl mainMenu = new MainMenuViewImpl();

        initViewsAndControllers(mainMenu);

        deSerialization(list);

        CheckTasksThread threadDemon = new CheckTasksThread(list);
        threadDemon.setDaemon(true);
        threadDemon.start();

        for(Task task: list) {
            uniqueTasksID.add(task.getID());
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            mainMenu.doWork(reader, list);
        } catch (IOException e) {
            logger.error("BufferedReader problem", e);
        }

        serialization(list);
    }

    /**
     * Method initViewsAndControllers realizes initialisation View and Controller objects.
     *
     * @param mainMenu of type MainMenuImplView
     */
    public static void initViewsAndControllers(MainMenuViewImpl mainMenu) {
        NextFiveTasksControllerImpl nextFiveTasksControllerImpl = new NextFiveTasksControllerImpl();
        NextFiveTasksViewImpl nextFiveTasksViewImpl = new NextFiveTasksViewImpl();
        nextFiveTasksControllerImpl.setNextFiveTasksView(nextFiveTasksViewImpl);
        nextFiveTasksViewImpl.setNextFiveTasksController(nextFiveTasksControllerImpl);

        CalendarForTodayControllerImpl calendarForTodayControllerImpl = new CalendarForTodayControllerImpl();
        CalendarForTodayViewImpl calendarForTodayViewImpl = new CalendarForTodayViewImpl();
        calendarForTodayControllerImpl.setCalendarForTodayView(calendarForTodayViewImpl);
        calendarForTodayViewImpl.setCalendarForTodayController(calendarForTodayControllerImpl);

        CalendarForWeekControllerImpl calendarForWeekControllerImpl = new CalendarForWeekControllerImpl();
        CalendarForWeekViewImpl calendarForWeekViewImpl = new CalendarForWeekViewImpl();
        calendarForWeekControllerImpl.setCalendarForWeekView(calendarForWeekViewImpl);
        calendarForWeekViewImpl.setCalendarForWeekController(calendarForWeekControllerImpl);

        AllTasksControllerImpl allTasksControllerImpl = new AllTasksControllerImpl();
        AllTasksViewImpl allTasksViewImpl = new AllTasksViewImpl();
        allTasksControllerImpl.setAllTasksView(allTasksViewImpl);
        allTasksViewImpl.setAllTasksControllerImpl(allTasksControllerImpl);

        ViewTasksViewImpl viewTasksViewImpl = new ViewTasksViewImpl();
        viewTasksViewImpl.setNextFiveTasksView(nextFiveTasksViewImpl);
        viewTasksViewImpl.setCalendarForTodayView(calendarForTodayViewImpl);
        viewTasksViewImpl.setCalendarForWeekView(calendarForWeekViewImpl);
        viewTasksViewImpl.setAllTasksView(allTasksViewImpl);


        AddNewTaskControllerImpl addNewTaskControllerImpl = new AddNewTaskControllerImpl();
        AddNewTaskViewImpl addNewTaskViewImpl = new AddNewTaskViewImpl();
        addNewTaskControllerImpl.setAddNewTaskView(addNewTaskViewImpl);
        addNewTaskViewImpl.setAddNewTaskController(addNewTaskControllerImpl);


        ChangeNameControllerImpl changeNameControllerImpl = new ChangeNameControllerImpl();
        ChangeNameViewImpl changeNameViewImpl = new ChangeNameViewImpl();
        changeNameControllerImpl.setChangeNameView(changeNameViewImpl);
        changeNameViewImpl.setChangeNameControllerImpl(changeNameControllerImpl);

        ChangeActivityControllerImpl changeActivityControllerImpl = new ChangeActivityControllerImpl();
        ChangeActivityViewImpl changeActivityViewImpl = new ChangeActivityViewImpl();
        changeActivityControllerImpl.setChangeActivityView(changeActivityViewImpl);
        changeActivityViewImpl.setChangeActivityController(changeActivityControllerImpl);

        ChangeTimeControllerImpl changeTimeControllerImpl = new ChangeTimeControllerImpl();
        ChangeTimeViewImpl changeTimeViewImpl = new ChangeTimeViewImpl();
        changeTimeControllerImpl.setChangeTimeView(changeTimeViewImpl);
        changeTimeViewImpl.setChangeTimeControllerImpl(changeTimeControllerImpl);

        DeleteTaskControllerImpl deleteTaskControllerImpl = new DeleteTaskControllerImpl();
        DeleteTaskViewImpl deleteTaskViewImpl = new DeleteTaskViewImpl();
        deleteTaskControllerImpl.setDeleteTaskView(deleteTaskViewImpl);
        deleteTaskViewImpl.setDeleteTaskControllerImpl(deleteTaskControllerImpl);

        ChangeDeleteTaskViewImpl changeDeleteTaskViewImpl = new ChangeDeleteTaskViewImpl();
        changeDeleteTaskViewImpl.setChangeNameView(changeNameViewImpl);
        changeDeleteTaskViewImpl.setChangeActivityView(changeActivityViewImpl);
        changeDeleteTaskViewImpl.setChangeTimeView(changeTimeViewImpl);
        changeDeleteTaskViewImpl.setDeleteTaskView(deleteTaskViewImpl);

        mainMenu.setViewTasks(viewTasksViewImpl);
        mainMenu.setAddDeleteTasks(addNewTaskViewImpl);
        mainMenu.setChangeDeleteTasks(changeDeleteTaskViewImpl);
    }

}

