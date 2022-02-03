package duke.task;

import static duke.task.TaskType.EVENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime by;

    /**
     * Defines an event.
     * @param description Description of task to do.
     * @param by Time of event.
     */
    public Event(String description, LocalDateTime by) {
        super(description, EVENT);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.getSymbol() + super.toString()
                + " (at: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format("%s | %s", super.convertToSaveFormat(), by.format((format)));
    }
}
