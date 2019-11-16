package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Objects;

public class LinkedTaskList {
    private int size = 0;
    private Node<Task> first;
    private Node<Task> last;

    private class Node<Task> {
        Task item;
        Node<Task> next;
        Node<Task> prev;

        Node(Node<Task> prev, Task element, Node<Task> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(Task task) {
        Node<Task> temp;
        if (size == 0) {
            first = new Node<>(null, task, null);
        } else if (size == 1) {
            last = new Node<>(first, task, null);
            first.next = last;
        } else {
            temp = last; //temp.last присвоилось автоматически
            last = new Node<>(temp, task, null);
            temp.next = last;
        }
        size++;
    }

    public boolean remove(Task task) {
        /*обрабатываем случай, когда первый
        элемент совпал с task.*/
        if (task.hashCode() == first.item.hashCode() &&
                task.equals(first.item)) {
            return removeFirstItem();
        }

        /*обрабатываем случай, когда последний
        элемент совпал с task.*/
        if (task.hashCode() == last.item.hashCode() &&
                task.equals(last.item)) {
            return removeLastItem();
        }

        /*обрабатываем другие случаи с совпадающим
        task при size > 2 или если совпадений нет.*/
        return anotherCase(task);
    }
    private boolean removeFirstItem() {
        if (size == 1) {
            first = null;
            size--;
            return true;
        } else if (size == 2) {
            first.item = last.item;
            first.prev = null;
            first.next = null;
            last = null;
            size--;
            return true;
        } else { //size > 2
            first.item = first.next.item;
            first.next = first.next.next;
            first.next.prev = first;
            size--;
            return true;
        }
    }
    private boolean removeLastItem() {
        if (size == 2) {
            last = null;
            size--;
            return true;
        } else { //size > 2
            last.item = last.prev.item;
            last.prev = last.prev.prev;
            last.prev.next = last;
            size--;
            return true;
        }
    }
    private boolean anotherCase(Task task) {
        Node<Task> temp = first.next;
        while (true) {
            if (task.hashCode() == temp.item.hashCode() &&
                    task.equals(temp.item)) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                size--;
                return true;
            }
            if (temp.next == null) {
                return false;
            }
            temp = temp.next;
        }
    }

    public int size() {
        return size;
    }

    public Task getTask(int index) {
        if (index >= size && index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<Task> temp = first;
        int number = 0;
        while (number < index) {
            temp = temp.next;
            number++;
        }
        return temp.item;
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList cutList = new LinkedTaskList();
        Node<Task> temp = first;
        while (temp != null) {
            Task task = temp.item;
            int incoming = task.nextTimeAfter(from);
            if (incoming < to && incoming != -1) {
                cutList.add(task);
            }
            temp = temp.next;
        }
        return cutList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList that = (LinkedTaskList) o;

        if (size == that.size) {
            for (int i = 0; i < size; i++) {
                Task task1 = this.getTask(i);
                Task task2 = that.getTask(i);
                if (!task1.equals(task2)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
//        int firstItemTime = first.item.getTime();
//        int lastItemTime = last.item.getTime();
        return 31* size;//*firstItemTime*lastItemTime;
    }
}