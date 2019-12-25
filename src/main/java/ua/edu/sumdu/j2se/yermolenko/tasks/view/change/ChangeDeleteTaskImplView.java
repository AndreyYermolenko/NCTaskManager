package ua.edu.sumdu.j2se.yermolenko.tasks.view.change;

import ua.edu.sumdu.j2se.yermolenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.yermolenko.tasks.model.Task;
import ua.edu.sumdu.j2se.yermolenko.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import static ua.edu.sumdu.j2se.yermolenko.tasks.Main.uniqueTasksID;
import static ua.edu.sumdu.j2se.yermolenko.tasks.model.TaskIO.serialization;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printErr;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.Print.printText;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.parseID;
import static ua.edu.sumdu.j2se.yermolenko.tasks.view.ServiceMethods.validateNumberMenu;

public class ChangeDeleteTaskImplView implements View {
    private BufferedReader reader;
    private TaskExist taskExist = new TaskExist();
    private View view1;
    private View view2;
    private View view3;
    private View view4;

    public void setView1(View view1) {
        this.view1 = view1;
    }

    public void setView2(View view2) {
        this.view2 = view2;
    }

    public void setView3(View view3) {
        this.view3 = view3;
    }

    public void setView4(View view4) {
        this.view4 = view4;
    }

    @Override
    public void doWork(Object...args) {
        this.reader = (BufferedReader) args[0];
        AbstractTaskList listTasks = (AbstractTaskList) args[1];

        taskExist.setTaskExist(true);
        Task task = null;
        String inputLine;
        String rexEx = "[1-4]";
        int number;
        int ID = inputID(reader);

        for(Task item: listTasks) {
            if (item.getID() == ID) {
                task = item;
            }
        }

        while (ID != 0 && taskExist.getTaskExist()) {
            doShow(true);

            try {
                inputLine = reader.readLine();
                if ("".equals(inputLine)) {
                    serialization(listTasks);
                    break;
                }
                if (validateNumberMenu(inputLine, rexEx)) {
                    number = Integer.parseInt(inputLine);
                    chooseMainCase(reader, listTasks, task, number);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                printErr("WARNING: Сделайте правильный ввод!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void chooseMainCase(BufferedReader reader, AbstractTaskList list, Task task, int number) {
        switch (number) {
            case 1:
                view1.doWork(reader, task);
                break;
            case 2:
                view2.doWork(reader, task);
                break;
            case 3:
                view3.doWork(reader, task);
                break;
            case 4:
                view4.doWork(reader, task, list, taskExist);
                break;
        }
    }

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
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doShow(Object o) {
        System.out.println(
                "3.1 Изменить название." + "\n"
                        + "3.2 Изменить активность." + "\n"
                        + "3.3 Изменить время." + "\n"
                        + "3.4 Удалить задачу." + "\n"
                        + "Для сохранения изменений и выхода в главное меню нажмите Enter." + "\n"
                        + "Ваш ввод: ");
    }

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
