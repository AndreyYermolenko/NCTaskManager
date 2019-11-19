package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayTaskList extends AbstractTaskList {
    private int initialSize = 10;
    private Task[] arrayTask = new Task[initialSize];
    private int size = 0;


    public ArrayTaskList() {
    }
    public ArrayTaskList(int initialSize) {
        this.initialSize = initialSize;
    }

    @Override
    public AbstractTaskList createList() {
        return new ArrayTaskList();
    }

    public void add(Task task) {
        int sizeArr = arrayTask.length;
        if (size < sizeArr) {
            arrayTask[size] = task;
        } else {
            arrayTask = arrayExpansion(arrayTask);
            arrayTask[size] = task;
        }
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

    public boolean remove(Task task) {
        for (int i = 0; i < arrayTask.length; i++) {
            Task taskCompare = arrayTask[i];
            //чтобы ускорить процесс -- сначала проверяем хеш-код
            if (task.hashCode() == taskCompare.hashCode() &&  //вызовутся обе функции, если хеши разные?
                    task.equals(taskCompare)) {
                arrayTask[i] = null;
                arrayTask = shiftArray(arrayTask, i); //нужно ли делать возврат ссылки на массив явно?
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

    public int size() {
        return size;
    }

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
        int result = Objects.hash(initialSize, size);
        result = 31 * result + Arrays.hashCode(arrayTask);
        return result;
    }

    @Override
    public ArrayTaskList clone() {
        ArrayTaskList list = new ArrayTaskList();
        Iterator<Task> iterator = this.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().clone());
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
}
