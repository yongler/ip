package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.task.TaskType.EVENT;

public class Event extends Task {
    protected LocalDateTime by;

    public Event(String description, LocalDateTime by) {
        super(description, EVENT);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.getSymbol() + super.toString() + " (at: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format("%s | %s", super.convertToSaveFormat(), by.format((format)));
    }
}
