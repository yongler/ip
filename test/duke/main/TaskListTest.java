package duke.main;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void TaskList() {
        TaskList tasks = new TaskList();
        assertEquals(new ArrayList<Task>(), tasks);
    }

}