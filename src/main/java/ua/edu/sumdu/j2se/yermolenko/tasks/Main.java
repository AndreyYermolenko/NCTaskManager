package ua.edu.sumdu.j2se.yermolenko.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.CheckTasksThread;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.*;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.AllTasksImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.CalendarForTodayImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.CalendarForWeekImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.controller.impl.NextFiveTasksImplController;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.*;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeActivityImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeNameImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.ChangeTimeImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.DeleteTaskImplView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.impl.MainMenuImplView;

import java.io.*;
import java.util.HashSet;

import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.deSerialization;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.serialization;

public class Main {
    public static HashSet<Integer> uniqueTasksID = new HashSet<>();
    private final static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        AbstractTaskList list = new ArrayTaskList();
        MainMenuImplView mainMenu = new MainMenuImplView();

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
            logger.error(e);
        }

        serialization(list);
    }

    /**
     * Method initViewsAndControllers realizes initialisation View and Controller objects.
     *
     * @param mainMenu of type MainMenuImplView
     */
    public static void initViewsAndControllers(MainMenuImplView mainMenu) {
        NextFiveTasksImplController nextFiveTasksController = new NextFiveTasksImplController();
        NextFiveTasksImplView nextFiveTasksView = new NextFiveTasksImplView();
        nextFiveTasksController.setNextFiveTasksView(nextFiveTasksView);
        nextFiveTasksView.setNextFiveTasksController(nextFiveTasksController);

        CalendarForTodayImplController calendarForTodayImplController = new CalendarForTodayImplController();
        CalendarForTodayImplView calendarForTodayImplView = new CalendarForTodayImplView();
        calendarForTodayImplController.setCalendarForTodayView(calendarForTodayImplView);
        calendarForTodayImplView.setCalendarForTodayController(calendarForTodayImplController);

        CalendarForWeekImplController calendarForWeekImplController = new CalendarForWeekImplController();
        CalendarForWeekImplView calendarForWeekImplView = new CalendarForWeekImplView();
        calendarForWeekImplController.setCalendarForWeekView(calendarForWeekImplView);
        calendarForWeekImplView.setCalendarForWeekController(calendarForWeekImplController);

        AllTasksImplController allTasksImplController = new AllTasksImplController();
        AllTasksImplView allTasksImplView = new AllTasksImplView();
        allTasksImplController.setAllTasksView(allTasksImplView);
        allTasksImplView.setAllTasksImplController(allTasksImplController);

        ViewTasksImplView viewTasksImplView = new ViewTasksImplView();
        viewTasksImplView.setNextFiveTasksView(nextFiveTasksView);
        viewTasksImplView.setCalendarForTodayView(calendarForTodayImplView);
        viewTasksImplView.setCalendarForWeekView(calendarForWeekImplView);
        viewTasksImplView.setAllTasksView(allTasksImplView);


        AddNewTaskImplController addNewTaskImplController = new AddNewTaskImplController();
        AddNewTaskImplView addNewTaskImplView = new AddNewTaskImplView();
        addNewTaskImplController.setAddNewTaskView(addNewTaskImplView);
        addNewTaskImplView.setAddNewTaskController(addNewTaskImplController);


        ChangeNameImplController changeNameImplController = new ChangeNameImplController();
        ChangeNameImplView changeNameImplView = new ChangeNameImplView();
        changeNameImplController.setChangeNameView(changeNameImplView);
        changeNameImplView.setChangeNameImplController(changeNameImplController);

        ChangeActivityImplController changeActivityImplController = new ChangeActivityImplController();
        ChangeActivityImplView changeActivityImplView = new ChangeActivityImplView();
        changeActivityImplController.setChangeActivityView(changeActivityImplView);
        changeActivityImplView.setChangeActivityController(changeActivityImplController);

        ChangeTimeImplController changeTimeImplController = new ChangeTimeImplController();
        ChangeTimeImplView changeTimeImplView = new ChangeTimeImplView();
        changeTimeImplController.setChangeTimeView(changeTimeImplView);
        changeTimeImplView.setChangeTimeImplController(changeTimeImplController);

        DeleteTaskImplController deleteTaskImplController = new DeleteTaskImplController();
        DeleteTaskImplView deleteTaskImplView = new DeleteTaskImplView();
        deleteTaskImplController.setDeleteTaskView(deleteTaskImplView);
        deleteTaskImplView.setDeleteTaskImplController(deleteTaskImplController);

        ChangeDeleteTaskImplView changeDeleteTaskImplView = new ChangeDeleteTaskImplView();
        changeDeleteTaskImplView.setChangeNameView(changeNameImplView);
        changeDeleteTaskImplView.setChangeActivityView(changeActivityImplView);
        changeDeleteTaskImplView.setChangeTimeView(changeTimeImplView);
        changeDeleteTaskImplView.setDeleteTaskView(deleteTaskImplView);

        mainMenu.setViewTasks(viewTasksImplView);
        mainMenu.setAddDeleteTasks(addNewTaskImplView);
        mainMenu.setChangeDeleteTasks(changeDeleteTaskImplView);
    }

}

