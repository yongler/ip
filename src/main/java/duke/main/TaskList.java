package duke.main;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
