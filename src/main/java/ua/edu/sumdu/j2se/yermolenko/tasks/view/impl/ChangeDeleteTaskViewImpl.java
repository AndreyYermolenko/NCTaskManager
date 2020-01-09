package ua.edu.sumdu.j2se.yermolenko.tasks.view.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.interfaces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.Main.uniqueTasksID;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.serialization;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.parseID;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.validateNumberMenu;

/**
 * Class ChangeDeleteTaskImplView realizes change and delete tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ChangeDeleteTaskViewImpl implements ChangeDeleteTaskView {
    private TaskExist taskExist = new TaskExist();
    private ChangeNameView changeNameView;
    private ChangeActivityView changeActivityView;
    private ChangeTimeView changeTimeView;
    private DeleteTaskView deleteTaskView;
    private final static Logger logger = LogManager.getLogger(ChangeDeleteTaskViewImpl.class);


    /**
     * Method setChangeNameView sets the changeNameView of this ChangeDeleteTaskImplView object.
     *
     *
     *
     * @param changeNameView the changeNameView of this ChangeDeleteTaskImplView object.
     *
     */
    public void setChangeNameView(ChangeNameView changeNameView) {
        this.changeNameView = changeNameView;
    }

    /**
     * Method setChangeActivityView sets the changeActivityView of this ChangeDeleteTaskImplView object.
     *
     *
     *
     * @param changeActivityView the changeActivityView of this ChangeDeleteTaskImplView object.
     *
     */
    public void setChangeActivityView(ChangeActivityView changeActivityView) {
        this.changeActivityView = changeActivityView;
    }

    /**
     * Method setChangeTimeView sets the changeTimeView of this ChangeDeleteTaskImplView object.
     *
     *
     *
     * @param changeTimeView the changeTimeView of this ChangeDeleteTaskImplView object.
     *
     */
    public void setChangeTimeView(ChangeTimeView changeTimeView) {
        this.changeTimeView = changeTimeView;
    }

    /**
     * Method setDeleteTaskView sets the deleteTaskView of this ChangeDeleteTaskImplView object.
     *
     *
     *
     * @param deleteTaskView the deleteTaskView of this ChangeDeleteTaskImplView object.
     *
     */
    public void setDeleteTaskView(DeleteTaskView deleteTaskView) {
        this.deleteTaskView = deleteTaskView;
    }

    /**
     * Method doWork realizes change and delete tasks.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     */
    @Override
    public void doWork(BufferedReader reader, AbstractTaskList list) {

        taskExist.setTaskExist(true);
        Task task = null;
        String inputLine;
        String rexEx = "[1-4]";
        int number;
        int ID = inputID(reader);

        for(Task item: list) {
            if (item.getID() == ID) {
                task = item;
            }
        }

        while (ID != 0 && taskExist.getTaskExist()) {
            doShow();

            try {
                inputLine = reader.readLine();
                if ("".equals(inputLine)) {
                    serialization(list);
                    break;
                }
                if (validateNumberMenu(inputLine, rexEx)) {
                    number = Integer.parseInt(inputLine);
                    chooseMainCase(reader, list, task, number);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                printErr("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                logger.error("Problem deleting/changing a task", e);
            }
        }
    }

    /**
     * Method chooseMainCase realizes choose View object.
     *
     * @param reader of type BufferedReader
     * @param list of type AbstractTaskList
     * @param task of type Task
     * @param number of type int
     */
    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, Task task, int number) {
        switch (number) {
            case 1:
                changeNameView.doWork(reader, task);
                break;
            case 2:
                changeActivityView.doWork(reader, task);
                break;
            case 3:
                changeTimeView.doWork(reader, task);
                break;
            case 4:
                deleteTaskView.doWork(reader, task, list, taskExist);
                break;
        }
    }

    /**
     * Method inputID realizes input ID.
     *
     * @param reader of type BufferedReader
     * @return int
     */
    private int inputID(BufferedReader reader) {
        while(true) {
            printText("Введите ID задачи: трёхзначное целое число." + "\n"
                    + "Или введите 0 для выхода в главное меню.");
            try {
                String line = reader.readLine();
                if("0".equals(line)) {
                    return 0;
                }
                int ID = parseID(line);
                if (uniqueTasksID.contains(ID)) {
                    return ID;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                printErr("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Method doShow shows menu.
     */
    @Override
    public void doShow() {
        System.out.println(
                "3.1 Изменить название." + "\n"
                        + "3.2 Изменить активность." + "\n"
                        + "3.3 Изменить время." + "\n"
                        + "3.4 Удалить задачу." + "\n"
                        + "Для сохранения изменений и выхода в главное меню нажмите Enter." + "\n"
                        + "Ваш ввод: ");
    }

    /**
     * Class TaskExist realizes check task exist.
     *
     * @author AndreyYermolenko
     * Created on 03.01.2020
     */
    public class TaskExist {
        private Boolean taskExist;

        public Boolean getTaskExist() {
            return taskExist;
        }

        public void setTaskExist(Boolean taskExist) {
            this.taskExist = taskExist;
        }
    }
}
