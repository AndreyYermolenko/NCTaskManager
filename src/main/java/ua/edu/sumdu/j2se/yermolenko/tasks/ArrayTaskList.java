package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Arrays;
import java.util.Objects;

public class ArrayTaskList {
    private int initialSize = 10;
    private Task[] arrayTask = new Task[initialSize];
    private int currentIndex = 0;


    public ArrayTaskList() {
    }
    public ArrayTaskList(int initialSize) {
        this.initialSize = initialSize;
    }

    public void add(Task task) {
        int sizeArr = arrayTask.length;
        if (currentIndex < sizeArr) {
            arrayTask[currentIndex] = task;
        } else {
            arrayTask = arrayExpansion(arrayTask);
            arrayTask[currentIndex] = task;
        }
        currentIndex++;
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
                currentIndex--;
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
        return currentIndex;
    }

    public Task getTask(int index) {
        if (index >= currentIndex && index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return arrayTask[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList cutList = new ArrayTaskList();
        for (int i = 0; i < currentIndex; i++) {
            Task task = arrayTask[i];
            int incoming = task.nextTimeAfter(from);
            if (incoming < to && incoming != -1) {
                cutList.add(task);
            }
        }
        return cutList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return initialSize == that.initialSize &&
                currentIndex == that.currentIndex &&
                Arrays.equals(arrayTask, that.arrayTask);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(initialSize, currentIndex);
        result = 31 * result + Arrays.hashCode(arrayTask);
        return result;
    }
}
