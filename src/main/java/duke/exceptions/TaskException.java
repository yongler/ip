package duke.exceptions;

public class TaskException extends DukeException {
    public static final String TASK_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TODO_ERROR = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EVENT_ERROR = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String DEADLINE_ERROR = "☹ OOPS!!! The description of a deadline cannot be empty.";

    private String debugMessage;

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, String debugMessage) {
        super(message);
        this.debugMessage = debugMessage;
    }
}
