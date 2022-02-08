package duke.task;
import java.time.LocalDateTime;

import static duke.task.TaskType.TODO;

/**
 * Todo class.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description, TODO, null);
    }

    @Override
    public String toString() {
        return super.getSymbol() + super.toString();
    }

    @Override
    public String convertToSaveFormat() {
        return String.format("%s", super.convertToSaveFormat());
    }
}
