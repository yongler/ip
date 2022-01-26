package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task() {}

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String convertToSaveFormat() {
        return String.format("%s | %s | %s", getSymbol(), getStatusIcon(), description);
    }
}
