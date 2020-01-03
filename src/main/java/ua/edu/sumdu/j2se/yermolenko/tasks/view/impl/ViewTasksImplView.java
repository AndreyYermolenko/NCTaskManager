package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.*;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;

/**
 * Class ViewTasksImplView realizes View menu.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ViewTasksImplView implements ViewTasksView {
    private NextFiveTasksView nextFiveTasksView;
    private CalendarForTodayView calendarForTodayView;
    private CalendarForWeekView calendarForWeekView;
    private AllTasksView allTasksView;
    private final static Logger logger = LogManager.getLogger(ViewTasksImplView.class);


    /**
     * Method setNextFiveTasksView sets the nextFiveTasksView of this ViewTasksImplView object.
     *
     *
     *
     * @param nextFiveTasksView the nextFiveTasksView of this ViewTasksImplView object.
     *
     */
    public void setNextFiveTasksView(NextFiveTasksView nextFiveTasksView) {
        this.nextFiveTasksView = nextFiveTasksView;
    }

    /**
     * Method setCalendarForTodayView sets the calendarForTodayView of this ViewTasksImplView object.
     *
     *
     *
     * @param calendarForTodayView the calendarForTodayView of this ViewTasksImplView object.
     *
     */
    public void setCalendarForTodayView(CalendarForTodayView calendarForTodayView) {
        this.calendarForTodayView = calendarForTodayView;
    }

    /**
     * Method setCalendarForWeekView sets the calendarForWeekView of this ViewTasksImplView object.
     *
     *
     *
     * @param calendarForWeekView the calendarForWeekView of this ViewTasksImplView object.
     *
     */
    public void setCalendarForWeekView(CalendarForWeekView calendarForWeekView) {
        this.calendarForWeekView = calendarForWeekView;
    }

    /**
     * Method setAllTasksView sets the allTasksView of this ViewTasksImplView object.
     *
     *
     *
     * @param allTasksView the allTasksView of this ViewTasksImplView object.
     *
     */
    public void setAllTasksView(AllTasksView allTasksView) {
        this.allTasksView = allTasksView;
    }

    /**
     * Method doWork realizes View menu.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        String inputLine;
        String rexEx = "[1-4]";
        int number;
        doShow();

        try {
            inputLine = reader.readLine();
            if (validateNumberMenu(inputLine, rexEx)) {
                number = Integer.parseInt(inputLine);
                chooseMainCase(reader, list, number);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            printErr("WARNING: Сделайте правильный ввод!");
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * Method chooseMainCase realizes choose View object.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     * @param number of type int
     */
    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, int number) {
        switch (number) {
            case 1:
                nextFiveTasksView.doWork(reader, list);
                break;
            case 2:
                calendarForTodayView.doWork(reader, list);
                break;
            case 3:
                calendarForWeekView.doWork(reader, list);
                break;
            case 4:
                allTasksView.doWork(reader, list);
                break;
        }
    }

    /**
     * Method doShow shows view menu.
     */
    @Override
    public void doShow() {
        System.out.println(
                "1.1 Следующие 5 задач." + "\n"
                        + "1.2 Календарь на сегодня." + "\n"
                        + "1.3 Календарь на неделю." + "\n"
                        + "1.4 Все задачи." + "\n"
                        + "Ваш ввод: ");
    }
}
