package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Class ArrayTaskList realizes ArrayList for Tasks.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class ArrayTaskList extends AbstractTaskList {
    private static final long serialVersionUID = 1;
    private int initialSize = 10;
    private Task[] arrayTask;
    private int size = 0;
    private final static Logger logger = LogManager.getLogger(ArrayTaskList.class);


    /**
     * Constructor ArrayTaskList creates a new ArrayTaskList instance.
     */
    public ArrayTaskList() {
        arrayTask = new Task[initialSize];
    }
    /**
     * Constructor ArrayTaskList creates a new ArrayTaskList instance.
     *
     * @param initSize of type int
     */
    public ArrayTaskList(int initSize) {
        arrayTask = new Task[initSize];
    }

    /**
     * The method adds a new task.
     * @param task of type Task.
     */
    public void add(Task task) {
        int sizeArr = arrayTask.length;
        if (size >= sizeArr) {
            arrayTask = arrayExpansion(arrayTask);
        }
        arrayTask[size] = task;
        size++;
    }

    private Task[] arrayExpansion(Task[] arrOld) {
        int sizeNewArr = arrOld.length*2;
        Task[] arrNew = new Task[sizeNewArr];
        for (int i = 0; i < arrOld.length; i++) {
            arrNew[i] = arrOld[i];
        }
        return arrNew;
    }

    /**
     * The method deletes the task and returns true
     * if the deletion is successful.
     * @param task of type Task.
     * @return true or false
     */
    public boolean remove(Task task) {
        for (int i = 0; i < arrayTask.length; i++) {
            Task taskCompare = arrayTask[i];
            if (task.hashCode() == taskCompare.hashCode() &&
                    task.equals(taskCompare)) {
                arrayTask[i] = null;
                arrayTask = shiftArray(arrayTask, i);
                size--;
                return true;
            }
        }
        return false;
    }

    private Task[] shiftArray(Task[] arrayTask, int shiftIdx) {
        for (int i = shiftIdx; i < arrayTask.length - 1; i++) {
            arrayTask[i] = arrayTask[i + 1];
        }
        return arrayTask;
    }

    /**
     * The method returns the size of the list.
     * @return size of type int
     */
    public int size() {
        return size;
    }

    /**
     * The method returns the task with the specified index.
     * @param index
     * @return Task
     */
    public Task getTask(int index) {
        if (index >= size && index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return arrayTask[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;

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
        return 31 * size;
    }

    @Override
    public ArrayTaskList clone() {
        ArrayTaskList list = null;
        try {
            list = (ArrayTaskList) super.clone();
            list.arrayTask = arrayTask.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("Problem cloning an ArrayTaskList", e);        }
        int index = 0;
        while (index < list.size) {
            list.arrayTask[index] = list.arrayTask[index].clone();
            index++;
        }
        return list;
    }

    @Override
    public Iterator<Task> iterator() {
        return new IteratorArrayList(this);
    }

    private class IteratorArrayList implements Iterator<Task> {
        private ArrayTaskList list;
        private int currentIndex = 0;
        private Task currentTask;

        public IteratorArrayList(ArrayTaskList list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < list.size();
        }

        @Override
        public Task next() {
            if (currentIndex >= list.size()) {
                throw new NoSuchElementException();
            }
            currentTask = list.getTask(currentIndex);
            currentIndex++;
            return currentTask;
        }

        @Override
        public void remove() {
            if (currentTask == null) {
                throw new IllegalStateException();
            }
            list.remove(currentTask);
            currentIndex--;
            currentTask = null;
        }
    }

    /**
     * The method implements sorting by the Shell method.
     */
    @Override
    public void sort() {
        int gap = this.size() / 2;
        while (gap >= 1) {
            for (int right = 0; right < this.size(); right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (this.getTask(c).compareTo(this.getTask(c + gap)) > 0) {
                        swap(c, c + gap);
                    }
                }
            }
            gap = gap / 2;
        }
    }

    private void swap(int ind1, int ind2) {
        Task tmp = this.getTask(ind1);
        arrayTask[ind1] = this.getTask(ind2);
        arrayTask[ind2] = tmp;
    }
}
