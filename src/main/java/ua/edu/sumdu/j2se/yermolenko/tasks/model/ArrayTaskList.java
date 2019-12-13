package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import java.util.*;

public class ArrayTaskList extends AbstractTaskList {
    private int initialSize = 10;
    private Task[] arrayTask;
    private int size = 0;



    public ArrayTaskList() {
        arrayTask = new Task[initialSize];
    }
    public ArrayTaskList(int initSize) {
        arrayTask = new Task[initSize];
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
        return 31 * size;
    }

    @Override
    public ArrayTaskList clone() {
        ArrayTaskList list = null;
        try {
            list = (ArrayTaskList) super.clone();
            list.arrayTask = arrayTask.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
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

    /*Метод сортировки Шелла*/
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
