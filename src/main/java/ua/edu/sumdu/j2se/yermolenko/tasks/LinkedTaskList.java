package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private int size = 0;
    private Node<Task> first;
    private Node<Task> last;

    private class Node<Task> implements Cloneable {
        Task item;
        Node<Task> next;
        Node<Task> prev;

        Node(Node<Task> prev, Task element, Node<Task> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public AbstractTaskList createList() {
        return new LinkedTaskList();
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

        if (index <= size/2) {
            return getTaskFromBegin(index);
        } else {
            return getTaskFromEnd(index);
        }
    }

    private Task getTaskFromBegin(int index){
        Node<Task> temp = first;
        int number = 0;
        while (number < index) {
            temp = temp.next;
            number++;
        }
        return temp.item;
    }
    private Task getTaskFromEnd(int index){
        Node<Task> temp = last;
        int number = size - 1;
        while (number > index) {
            temp = temp.prev;
            number--;
        }
        return temp.item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList that = (LinkedTaskList) o;

        if (size == that.size) {
            Iterator<Task> iterator1 = this.iterator();
            Iterator<Task> iterator2 = that.iterator();
            while (iterator1.hasNext()) {
                if (!iterator1.next().equals(iterator2.next())) {
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
        return 31* size;
    }

//    @Override
//    public LinkedTaskList clone() {
//        LinkedTaskList list = new LinkedTaskList();
//        Iterator<Task> iterator = this.iterator();
//        while (iterator.hasNext()) {
//            list.add(iterator.next().clone());
//        }
//        return list;
//    }

    @Override
    public LinkedTaskList clone() {
        LinkedTaskList list = null;
        try {
            list = (LinkedTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        list.first = list.last = null;
        list.size = 0;
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().clone());
        }
        return list;
    }

    @Override
    public Iterator<Task> iterator() {
        return new IteratorLinkedList(this, first);
    }

    private class IteratorLinkedList implements Iterator<Task> {
        private Node node;
        private Task currentTask;
        private LinkedTaskList list;

        public IteratorLinkedList(LinkedTaskList list, Node node) {
            this.list = list;
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Task next() {
            if (node == null) {
                throw new NoSuchElementException();
            }
            currentTask = (Task) node.item;
            node = node.next;
            return currentTask;
        }

        @Override
        public void remove() {
            if (currentTask == null) {
                throw new IllegalStateException();
            }
            list.remove(currentTask);
            currentTask = null;
        }
    }

    public Stream<Task> getStream() {
        LinkedList list = new LinkedList();
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list.stream();
    }
}
