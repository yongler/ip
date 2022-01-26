package duke.task;
import static duke.task.TaskType.TODO;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TODO);
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
