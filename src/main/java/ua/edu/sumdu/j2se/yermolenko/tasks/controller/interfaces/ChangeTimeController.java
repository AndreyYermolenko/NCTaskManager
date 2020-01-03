package ua.edu.sumdu.j2se.yermolenko.tasks.controller.interfaces;

/**
 * Interface ChangeTimeController describes class ChangeTimeImplController that realize change task time.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public interface ChangeTimeController {
    /**
     * Method doWork realizes change task time.
     *
     * @param args of type Object: Task task, LocalDateTime start, LocalDateTime end, int interval.
     */
    void doWork(Object...args);
}
