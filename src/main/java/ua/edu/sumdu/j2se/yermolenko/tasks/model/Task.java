package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task implements Cloneable, Serializable, Comparable<Task> {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeat;
    private static final long serialVersionUID = 1;

    public Task(String title, LocalDateTime time) {
        setTime(time);
        this.title = title;
        this.active = false;
    }

    public Task(String title, LocalDateTime time, boolean active) {
        this(title, time);
        this.active = active;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        setTime(start, end, interval);
        this.title = title;
        this.active = false;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval, boolean active) {
        this(title, start, end, interval);
        this.active = active;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /*Метод возвращает время выполнения
     * задачи. В случае, если задача
     * повторяется -- возвращает
     * время начала повторения.*/
    public LocalDateTime getTime() {
        if (!this.repeat) {
            return LocalDateTime.from(this.time);
        } else {
            return LocalDateTime.from(this.start);
        }
    }

    /*Метод возвращает время начала
     * повторения задачи. Если
     * задача не повторяется,
     * то возвращает время выполнения
     * задачи.*/
    public LocalDateTime getStartTime() {
        if (this.repeat) {
            return LocalDateTime.from(this.start);
        } else {
            return LocalDateTime.from(this.time);
        }
    }

    /*Метод возвращает время прекращения
     * повторения задачи.
     * Если задача не повторяется,
     * то возвращает время выполнения
     * задачи.*/
    public LocalDateTime getEndTime() {
        if (this.repeat) {
            return LocalDateTime.from(this.end);
        } else {
            return LocalDateTime.from(this.time);
        }
    }

    /*Метод возвращает временной интервал
     * между выполнениями задачи,
     * что повторяется (в секундах).
     * Если задача не повторяется,
     * то возвращает 0*/
    public int getRepeatInterval() {
        if (this.repeat) {
            return interval;
        } else {
            return 0;
        }
    }

    /*Метод устанавливает время выполнения
     * задачи. Если задача повторяется
     * (регулярная), то преобразует её в
     * такую, что не повторяется
     * (одноразовую).*/
    public void setTime(LocalDateTime time) {
        if (time == null) {
            System.out.println("Ошибка при установке времени!");
            throw new IllegalArgumentException();
        }
        this.time = time;
        this.repeat = false;
    }

    /*Метод устанавливает время
     * начала и окончания выполнения
     * повторяемой задачи и интервал
     * между повторениями. Если задача
     * не повторяется, делает её
     * повторяющейся.*/
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null
                || start.isAfter(end)
                || interval <= 0) {
            System.out.println("Ошибка при установке времени!");
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeat = true;
    }

    public boolean isRepeated() {
        return this.repeat;
    }

    /* Следующий момент времени должен
     * быть > current, но может быть
     * == end. */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null) {
            System.out.println("Ошибка при установке времени!");
            throw new IllegalArgumentException();
        }
        if (!isActive()) {
            return null;
        }

        if (!isRepeated()) {
            return current.isBefore(time) ? LocalDateTime.from(this.time) : null;
        } else if (current.isBefore(start)) {
            return LocalDateTime.from(this.start);
        } else if (current.isBefore(end)) {
            LocalDateTime tempTime = LocalDateTime.from(start);
            while (current.compareTo(tempTime) >= 0) {
                tempTime = tempTime.plusSeconds(interval);
            }
            return tempTime.compareTo(end) <= 0 ? tempTime : null;
        } else {
            return null;
        }
    }

    /*У объекта класса Task может быть два состояния:
     * repeat == true and repeat == false.
     * Для каждого состояния есть свой перечень
     * значимых параметров для сравнения.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        if (repeat == task.repeat && !repeat) {
            return title.equals(task.title) &&
                    time.isEqual(task.time) &&
                    active == task.active;
        } else if (repeat == task.repeat && repeat) {
            return title.equals(task.title) &&
                    start.isEqual(task.start) &&
                    end.isEqual(task.end) &&
                    interval == task.interval &&
                    active == task.active;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), repeat);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder line = new StringBuilder();
        if (!repeat) {
            return line.append("Task{")
                    .append("title='").append(title).append('\'')
                    .append(", time=").append(time.format(formatter))
                    .append(", active=").append(active)
                    .append(", repeat=").append(repeat).append('}')
                    .toString();
        } else {
            return line.append("Task{")
                    .append("title='").append(title).append('\'')
                    .append(", start=").append(start.format(formatter))
                    .append(", end=").append(end.format(formatter))
                    .append(", interval=").append(interval)
                    .append(", active=").append(active)
                    .append(", repeat=").append(repeat).append('}')
                    .toString();
        }

    }

    /*Объект класса LocalDateTime клонируется,
     * а не копируется ссылка.*/
    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int compareTo(@NotNull Task that) {
        LocalDateTime thisTime = this.nextTimeAfter(LocalDateTime.now());
        LocalDateTime thatTime = that.nextTimeAfter(LocalDateTime.now());
        if (thisTime == null && thatTime == null) {
            return 0;
        } else if (thisTime == null) {
            return -1;
        } else if (thatTime == null) {
            return 1;
        } else {
            return thisTime.compareTo(thatTime);
        }
    }
}
