package duke.exceptions;

public class DukeException extends Exception {
    public static final String TASK_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String TODO_ERROR = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EVENT_ERROR = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String DEADLINE_ERROR = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String STORE_TASKS_ERROR = "Failed to store tasks";

    public DukeException(String message) {
        super(message);
    }
}
