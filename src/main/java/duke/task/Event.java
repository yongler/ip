package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime by;

    public Event(String description, LocalDateTime by) {
        super(description, EVENT);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.getSymbol() + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        return String.format("%s | %s", super.convertToSaveFormat(), by);
    }
}
