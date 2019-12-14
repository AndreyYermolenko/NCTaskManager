package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

class AbstractTaskListTest {
    AbstractTaskList expectedArrayList = new ArrayTaskList();
    AbstractTaskList actualArrayList = new ArrayTaskList();
    AbstractTaskList expectedLinkedList = new LinkedTaskList();
    AbstractTaskList actualLinkedList = new LinkedTaskList();

    LocalDateTime dateStart1 = LocalDateTime.now().plusDays(1);
    LocalDateTime dateEnd1 = LocalDateTime.now().plusDays(3);
    int interval1 = 1000;
    LocalDateTime dateStart2 = LocalDateTime.now().plusDays(2);
    LocalDateTime dateEnd2 = LocalDateTime.now().plusDays(3);
    int interval2 = 2000;
    LocalDateTime dateStart3 = LocalDateTime.now().plusDays(3);
    LocalDateTime dateEnd3 = LocalDateTime.now().plusDays(10);
    int interval3 = 3000;
    LocalDateTime date4 = LocalDateTime.now().plusDays(4);
    LocalDateTime date5 = LocalDateTime.now().plusDays(5);
    LocalDateTime date6 = LocalDateTime.now().plusDays(6);

    Task task1 = new Task("task1", 0, dateStart1, dateEnd1, interval1, true);
    Task task2 = new Task("task2", 0, dateStart2, dateEnd2, interval2, true);
    Task task3 = new Task("task3", 0, dateStart3, dateEnd3, interval3, true);
    Task task4 = new Task("task4", 0, date4, false);
    Task task5 = new Task("task5", 0, date5, true);
    Task task6 = new Task("task6", 0, date6, true);


    @org.junit.jupiter.api.Test
    void sortArrayList() {
        expectedArrayList.add(task4);
        expectedArrayList.add(task1);
        expectedArrayList.add(task2);
        expectedArrayList.add(task3);
        expectedArrayList.add(task5);
        expectedArrayList.add(task6);

        actualArrayList.add(task6);
        actualArrayList.add(task5);
        actualArrayList.add(task4);
        actualArrayList.add(task3);
        actualArrayList.add(task2);
        actualArrayList.add(task1);

        actualArrayList.sort();

        assertEquals(expectedArrayList, actualArrayList);
    }

    @org.junit.jupiter.api.Test
    void sortLinkedList() {
        expectedLinkedList.add(task4);
        expectedLinkedList.add(task1);
        expectedLinkedList.add(task2);
        expectedLinkedList.add(task3);
        expectedLinkedList.add(task5);
        expectedLinkedList.add(task6);

        actualLinkedList.add(task6);
        actualLinkedList.add(task5);
        actualLinkedList.add(task4);
        actualLinkedList.add(task3);
        actualLinkedList.add(task2);
        actualLinkedList.add(task1);

        actualLinkedList.sort();

        assertEquals(expectedLinkedList, actualLinkedList);
    }
}