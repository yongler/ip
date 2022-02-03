package duke.task;

import static duke.task.TaskType.DEADLINE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Defines an deadline.
     * @param description Description of deadline to do.
     * @param by Time of deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return super.getSymbol() + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format("%s | %s", super.convertToSaveFormat(), by.format((format)));
    }
}
