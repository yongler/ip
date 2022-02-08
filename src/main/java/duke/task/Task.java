package duke.task;

import java.time.LocalDateTime;

/**
 * Task class.
 */
public class Task implements Comparable<Task>{
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    protected LocalDateTime by;

    public Task() {}
    /**
     * Defines a task.
     * @param description Description of task to do.
     * @param taskType Type of task.
     */
    public Task(String description, TaskType taskType, LocalDateTime by) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.by = by;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns symbol for printing task based on its type.
     *
     * @return [type] where type corresponds to task type.
     */
    public String getSymbol() {
        switch (taskType) {
        case TODO:
            return "[T]";
        case EVENT:
            return "[E]";
        case DEADLINE:
            return "[D]";
        default:
            return "";
        }
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns status icon when printing task.
     *
     * @return X if task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Formats task to be saved into backup file.
     *
     * @return Formatted string for saving into backup file.
     */
    public String convertToSaveFormat() {
        return String.format("%s | %s | %s", getSymbol(), getStatusIcon(), description);
    }

    public LocalDateTime getDeadline() {
        return by;
    }

    @Override
    public int compareTo(Task o) {
        if (getDeadline() == null || o.getDeadline() == null)
            return 0;
        return getDeadline().compareTo(o.getDeadline());
    }
}
