package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Wrapper class for Arraylist of tasks.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
