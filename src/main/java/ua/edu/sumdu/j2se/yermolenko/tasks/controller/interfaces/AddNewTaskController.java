package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;




/**
 * Interface AddNewTaskController describes class AddNewTaskImplController that realize add new task.
 *
 * @author AndreyYermolenko
 * Created on 02.01.2020
 */
public interface AddNewTaskController {

    /**
     * Method doWork realizes add new task.
     *
     * @param args of types AbstractTaskList, Task, LocalDateTime and int.
     */
    void doWork(Object...args);
}
