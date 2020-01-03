package ua.edu.sumdu.j2se.yermolenko.tasks.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class Task realizes create Task object.
 *
 * @author AndreyYermolenko
 * Created on 03.01.2020
 */
public class Task implements Cloneable, Serializable, Comparable<Task> {
    private String title;
    private final int ID;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeat;
    private static final long serialVersionUID = 1;
    private final static Logger logger = LogManager.getLogger(Task.class);

    /**
     * Constructor Task creates a new Task instance.
     *
     * @param title of type String
     * @param ID of type int
     * @param time of type LocalDateTime
     */
    public Task(String title, int ID, LocalDateTime time) {
        setTime(time);
        this.title = title;
        this.ID = ID;
        this.active = false;
    }

    /**
     * Constructor Task creates a new Task instance.
     *
     * @param title of type String
     * @param ID of type int
     * @param time of type LocalDateTime
     * @param active of type boolean
     */
    public Task(String title, int ID, LocalDateTime time, boolean active) {
        this(title, ID, time);
        this.active = active;
    }

    /**
     * Constructor Task creates a new Task instance.
     *
     * @param title of type String
     * @param ID of type int
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @param interval of type int
     */
    public Task(String title, int ID, LocalDateTime start, LocalDateTime end, int interval) {
        setTime(start, end, interval);
        this.title = title;
        this.ID = ID;
        this.active = false;
    }

    /**
     * Constructor Task creates a new Task instance.
     *
     * @param title of type String
     * @param ID of type int
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @param interval of type int
     * @param active of type boolean
     */
    public Task(String title, int ID, LocalDateTime start, LocalDateTime end, int interval, boolean active) {
        this(title, ID, start, end, interval);
        this.active = active;
    }

    /**
     * Method getTitle returns the title of this Task object.
     *
     *
     *
     * @return the title (type String) of this Task object.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Method setTitle sets the title of this Task object.
     *
     *
     *
     * @param title the title of this Task object.
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method getID returns the ID of this Task object.
     *
     *
     *
     * @return the ID (type int) of this Task object.
     */
    public int getID() {
        return ID;
    }

    /**
     * Method isActive returns the active of this Task object.
     *
     *
     *
     * @return the active (type boolean) of this Task object.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Method setActive sets the active of this Task object.
     *
     *
     *
     * @param active the active of this Task object.
     *
     */
    public void setActive(boolean active) {
        this.active = active;
    }



    /**
     * Method getTime returns the execution time of the task.
     * In case the task repeats, it returns the start time of the repeat.
     *
     * @return the time (type LocalDateTime) of this Task object.
     */
    public LocalDateTime getTime() {
        if (!this.repeat) {
            return LocalDateTime.from(this.time);
        } else {
            return LocalDateTime.from(this.start);
        }
    }

    /**
     * Method getStartTime returns the start time of the task.
     * If the task does not repeat, then returns the time the task completed.
     *
     * @return the startTime (type LocalDateTime) of this Task object.
     */
    public LocalDateTime getStartTime() {
        if (this.repeat) {
            return LocalDateTime.from(this.start);
        } else {
            return LocalDateTime.from(this.time);
        }
    }


    /**
     * Method getEndTime returns the end time the task repeats.
     * If the task does not repeat, then returns the time the task completed.
     *
     * @return the endTime (type LocalDateTime) of this Task object.
     */
    public LocalDateTime getEndTime() {
        if (this.repeat) {
            return LocalDateTime.from(this.end);
        } else {
            return LocalDateTime.from(this.time);
        }
    }

    /**
     * Method getRepeatInterval returns the time interval between task executions,
     * which is repeated (in seconds). If the task does not repeat,
     * then returns 0.
     *
     * @return the repeatInterval (type int) of this Task object.
     */
    public int getRepeatInterval() {
        if (this.repeat) {
            return interval;
        } else {
            return 0;
        }
    }

    /**
     * Method setTime sets the time to complete the task.
     * If the task is repeated (regular),
     * then it will be converted to one that does not repeat (one-time).
     *
     * @param time the time of this Task object.
     *
     */
    public void setTime(LocalDateTime time) {
        if (time == null) {
            System.out.println("Ошибка при установке времени!");
            throw new IllegalArgumentException();
        }
        this.time = time;
        this.repeat = false;
    }

    /**
     * Method setTime sets the start and end time of the repeated task
     * and the interval between repetitions.
     * If the task does not repeat, makes it repeat.
     *
     * @param start of type LocalDateTime
     * @param end of type LocalDateTime
     * @param interval of type int
     */
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

    /**
     * Method isRepeated returns the repeated of this Task object.
     *
     *
     *
     * @return the repeated (type boolean) of this Task object.
     */
    public boolean isRepeated() {
        return this.repeat;
    }



    /**
     * Method nextTimeAfter returns the next task execution time.
     * If the task is no longer running, it returns null.
     *
     * @param current of type LocalDateTime
     * @return LocalDateTime
     */
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
                    .append(", ID=").append(ID)
                    .append(", time=").append(time.format(formatter))
                    .append(", active=").append(active)
                    .append(", repeat=").append(repeat).append('}')
                    .toString();
        } else {
            return line.append("Task{")
                    .append("title='").append(title).append('\'')
                    .append(", ID=").append(ID)
                    .append(", start=").append(start.format(formatter))
                    .append(", end=").append(end.format(formatter))
                    .append(", interval= ").append(parseInterval(interval))
                    .append(", active=").append(active)
                    .append(", repeat=").append(repeat).append('}')
                    .toString();
        }

    }

    /**
     * Method getDescription returns the description of this Task object.
     *
     *
     *
     * @return the description (type String) of this Task object.
     */
    public String getDescription() {
        StringBuilder line = new StringBuilder();
        if (!repeat) {
            return line.append("Task{")
                    .append("title='").append(title).append('\'')
                    .append(", ID=").append(ID)
                    .append(", active=").append(active)
                    .append(", repeat=").append(repeat).append('}')
                    .toString();
        } else {
            return line.append("Task{")
                    .append("title='").append(title).append('\'')
                    .append(", ID=").append(ID)
                    .append(", active=").append(active)
                    .append(", repeat=").append(repeat)
                    .append(", interval= ").append(parseInterval(interval))
                    .append('}').toString();
        }
    }

    private StringBuilder parseInterval(int interval) {
        StringBuilder intervalString = new StringBuilder();
        int days = interval/86400;
        int hours = (interval % 86400) / 3600;
        int minutes = (interval - days*86400 - hours*3600) / 60;
        intervalString.append(days).append("d ")
                .append(hours).append("h ")
                .append(minutes).append("m");
        return intervalString;
    }

    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public int compareTo(Task that) {
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
