package duke.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import java.util.ArrayList;

class TaskListTest {

    @Test
    void TaskList() {
        TaskList tasks = new TaskList();
        assertEquals(new ArrayList<Task>(), tasks);
    }

}