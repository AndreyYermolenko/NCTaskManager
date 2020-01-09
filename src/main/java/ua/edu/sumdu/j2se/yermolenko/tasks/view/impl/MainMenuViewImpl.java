package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.AddNewTaskView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ChangeDeleteTaskView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.MainMenuView;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.ViewTasksView;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.validateNumberMenu;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;

/**
 * Class MainMenuImplView realizes main menu.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class MainMenuViewImpl implements MainMenuView {
    private ViewTasksView viewTasks;
    private AddNewTaskView addDeleteTasks;
    private ChangeDeleteTaskView changeDeleteTasks;
    private final static Logger logger = LogManager.getLogger(MainMenuViewImpl.class);


    /**
     * Method setViewTasks sets the viewTasks of this MainMenuImplView object.
     *
     *
     *
     * @param viewTasks the viewTasks of this MainMenuImplView object.
     *
     */
    public void setViewTasks(ViewTasksView viewTasks) {
        this.viewTasks = viewTasks;
    }

    /**
     * Method setAddDeleteTasks sets the addDeleteTasks of this MainMenuImplView object.
     *
     *
     *
     * @param addDeleteTasks the addDeleteTasks of this MainMenuImplView object.
     *
     */
    public void setAddDeleteTasks(AddNewTaskView addDeleteTasks) {
        this.addDeleteTasks = addDeleteTasks;
    }

    /**
     * Method setChangeDeleteTasks sets the changeDeleteTasks of this MainMenuImplView object.
     *
     *
     *
     * @param changeDeleteTasks the changeDeleteTasks of this MainMenuImplView object.
     *
     */
    public void setChangeDeleteTasks(ChangeDeleteTaskView changeDeleteTasks) {
        this.changeDeleteTasks = changeDeleteTasks;
    }

    /**
     * Method doWork realizes main menu.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    public void doWork(BufferedReader reader, AbstractTaskList list) {
        int number;
        String inputLine;
        String regEx = "[1-3]";

        while(true) {
            doShow();
            try {
                inputLine = reader.readLine();
                if ("4".equals(inputLine)) {
                    break;
                }
                if (validateNumberMenu(inputLine, regEx)) {
                    number = Integer.parseInt(inputLine);
                    chooseMainCase(reader, list, number);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                printErr("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                logger.error("The problem in the main menu", e);
            }
        }
    }

    /**
     * Method doShow main menu.
     */
    @Override
    public void doShow() {
        System.out.println("Главное меню." + "\n"
                + "1.Посмотреть список задач." + "\n"
                + "2.Добавить новую задачу." + "\n"
                + "3.Изменить/удалить задачу." + "\n"
                + "4.Завершение работы." + "\n"
                + "Ваш ввод: ");
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
                viewTasks.doWork(reader, list);
                break;
            case 2:
                addDeleteTasks.doWork(reader, list);
                break;
            case 3:
                changeDeleteTasks.doWork(reader, list);
                break;
        }
    }


}
