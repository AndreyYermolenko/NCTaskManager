package ua.edu.sumdu.j2se.yermolenko.tasks;

import java.util.Objects;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeat;

    public Task(String title, int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Время должно быть положительным числом!");
        }
        this.title = title;
        this.time = time;
        this.repeat = false;
        this.active = false;
    }

    public Task(String title, int start, int end, int interval) {
        if (start < 0 || end <= start || interval <= 0) {
            throw new IllegalArgumentException("Ошибка при установке времени!");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeat = true;
        this.active = false;
    }

    /*Метод возвращает название задачи.*/
    public String getTitle() {
        return this.title;
    }

    /*Метод изменяет название задачи.*/
    public void setTitle(String title) {
        this.title = title;
    }

    /*Метод считывает текущее состояние
     * задачи. Если задача активна возвра-
     * щает true.*/
    public boolean isActive() {
        return this.active;
    }

    /*Метод изменяет состояние задачи.*/
    public void setActive(boolean active) {
        this.active = active;
    }

    /*Метод возвращает время выполнения
     * задачи. В случае, если задача
     * повторяется -- возвращает
     * время начала повторения.*/
    public int getTime() {
        if (!this.repeat) {
            return this.time;
        } else {
            return this.start;
        }
    }

    /*Метод устанавливает время выполнения
     * задачи. Если задача повторяется
     * (регулярная), то преобразует её в
     * такую, что не повторяется
     * (одноразовую).*/
    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Время должно быть положительным числом!");
        }
        this.time = time;
        this.repeat = false;
    }

    /*Метод возвращает время начала
     * повторения задачи. Если
     * задача не повторяется,
     * то возвращает время выполнения
     * задачи.*/
    public int getStartTime() {
        if (this.repeat) {
            return this.start;
        } else {
            return this.time;
        }
    }

    /*Метод возвращает время прекращения
     * повторения задачи.
     * Если задача не повторяется,
     * то возвращает время выполнения
     * задачи.*/
    public int getEndTime() {
        if (this.repeat) {
            return this.end;
        } else {
            return this.time;
        }
    }

    /*Метод возвращает временной интервал
     * между выполнениями задачи,
     * что повторяется.
     * Если задача не повторяется,
     * то возвращает 0*/
    public int getRepeatInterval() {
        if (this.repeat) {
            return this.interval;
        } else {
            return 0;
        }
    }

    /*Метод устанавливает время
     * начала и окончания выполнения
     * повторяемой задачи и интервал
     * между повторениями. Если задача
     * не повторяется, делает её
     * повторяющейся.*/
    public void setTime(int start, int end, int interval) {
        if (start < 0 || end <= start || interval <= 0) {
            throw new IllegalArgumentException("Ошибка при установке времени!");
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        if (!this.repeat) {
            this.repeat = true;
        }
    }

    /*Метод возвращает true, если
     * задача повторяется.*/
    public boolean isRepeated() {
        return this.repeat;
    }

    /*1) Проверяет активность задачи.
     * Если false, возвращает -1.
     *
     * 2) Проверяет регулярность задачи.
     * Если false (задача одноразовая),
     * проверяет, что время выполнения
     * задачи еще не наступило.
     * Если так (true), то возвращает время
     * выполнения задачи, а если false --
     * возвращает -1.
     *
     * 3) Если задача регулярная, то
     * сравнивает текущее время с
     * временем первого выполнения
     * задачи. Если current < start,
     * то возвращает время первого
     * выполнения задачи -- start.
     *
     * 4) Если задача регулярная
     * и время current < end,
     * то вычисляет следующий
     * момент времени, где задача
     * могла быть выполнена.
     * Если nextTime попадает в пределы
     * < end, то возвращает его,
     * иначе -1.
     *
     * 5) Если ни одно из условий
     * не выполняется, то возвращает -1.*/
    public int nextTimeAfter(int current) {
        if (current < 0) {
            throw new IllegalArgumentException("Время должно быть положительным числом!");
        }
        if (!isActive()) {
            return -1;
        }

        if (!isRepeated()) {
            return current < time ? time : -1;
        } else if (current < start) {
            return start;
        } else if (current < end) {
            int countInt = (current - start) / interval;  //количество целых периодов между start и current
            int nextTime = start + interval * (countInt + 1); //следующий подходящий момент времени после current
            return nextTime < end ? nextTime : -1; //если вычислинное время < end, возвращает его
        } else {
            return -1;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTime() == task.getTime() &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                isActive() == task.isActive() &&
                repeat == task.repeat &&
                Objects.equals(getTitle(), task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTime(), start, end, interval, isActive(), repeat);
    }

    @Override
    public String toString() {
        return title;
    }
}
