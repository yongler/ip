package duke.main;

import java.io.FileNotFoundException;
import java.util.Collections;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Public class Duke for main Duke app.
 */
public class Duke {
    private Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Create duke object.
     * @param filePath Specify storage file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Create duke object without filepath.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * Returns the response associated with input by user to be displayed on GUI.
     * @param input String input from user.
     * @return String to be displayed on GUI.
     */
    public String getResponse(String input) {
        String[] str = Parser.splitInputStringBySpaces(input);
        String response = "";

        if (str[0].compareTo("list") == 0) {
            response = listTask();
        } else if (str[0].compareTo("mark") == 0) {
            response = markTask(str);
        } else if (str[0].compareTo("unmark") == 0) {
            response = unmarkTask(str);
        } else if (str[0].compareTo("delete") == 0) {
            response = deleteTask(str);
        } else if (str[0].compareTo("find") == 0) {
            response = findTask(str);
        } else if (str[0].equals("sort")) {
            response = sortTask();
        } else {
            try {
                response = addTask(str);
            } catch (DukeException e) {
                response = e.getMessage();
            }
        }
        try {
            storage.saveTasksToStorage(tasks);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Adds a new Todo to tasks.
     *
     * @param str The whole input string, split.
     * @throws DukeException if no details when adding.
     */
    private void addTodo(String[] str) throws DukeException {
        if (str.length == 1) {
            throw new DukeException(DukeException.TODO_ERROR);
        }

        StringBuilder stuff = new StringBuilder();
        for (int i = 1; i < str.length; i++) {
            stuff.append(str[i]).append(" ");
        }
        tasks.add(new Todo(stuff.toString()));
    }

    /**
     * Adds a new Deadline to tasks.
     *
     * @param str The whole input string, split.
     * @throws DukeException if no details when adding.
     */
    private void addDeadline(String[] str) throws DukeException {
        if (str.length == 1) {
            throw new DukeException(DukeException.DEADLINE_ERROR);
        }

        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < str.length; i++) {
            if (str[i].compareTo("/by") == 0) {
                for (int j = 1; j < i; j++) {
                    description.append(str[j]).append(" ");
                }
                for (int j = i + 1; j < str.length; j++) {
                    deadline.append(str[j]).append(" ");
                }
                break;
            }
        }
        tasks.add(new Deadline(description.toString(), Parser.parseDate(deadline.toString())));
    }

    /**
     * Adds a new Event to tasks.
     *
     * @param str The whole input string, split.
     * @throws DukeException if no details when adding.
     */
    private void addEvent(String[] str) throws DukeException {

        if (str.length == 1) {
            throw new DukeException(DukeException.EVENT_ERROR);
        }

        StringBuilder description = new StringBuilder();
        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < str.length; i++) {
            if (str[i].compareTo("/at") == 0) {
                for (int j = 1; j < i; j++) {
                    description.append(str[j]).append(" ");
                }
                for (int j = i + 1; j < str.length; j++) {
                    deadline.append(str[j]).append(" ");
                }
                break;
            }
        }
        tasks.add(new Event(description.toString(), Parser.parseDate(deadline.toString())));
    }

    /**
     * Adds a new task depending on type and append in to tasks.
     *
     * @param str The whole input string, split.
     * @return Response string for adding task.
     * @throws DukeException if no details when adding.
     */
    private String addTask(String[] str) throws DukeException {
        if (str[0].compareTo("deadline") == 0) {
            addDeadline(str);
        } else if (str[0].compareTo("todo") == 0) {
            addTodo(str);
        } else if (str[0].compareTo("event") == 0) {
            addEvent(str);
        } else {
            throw new DukeException(DukeException.TASK_ERROR);
        }

        ui.newMessage();
        ui.appendMessage("Got it. I've added this task: \n");
        ui.appendMessage(String.format("%s\n", tasks.get(tasks.size() - 1)));
        ui.appendMessage(String.format("Now you have %d tasks in the list.\n", tasks.size()));

        return ui.getMessage();
    }

    /**
     * Finds task given task number.
     * @param str Info about task number.
     * @return Response string for finding task.
     */
    private String findTask(String[] str) {
        String toFind = str[1];

        ui.newMessage();
        ui.appendMessage("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(toFind)) {
                ui.appendMessage(String.format("%d. %s\n", i + 1, task));
            }
        }
        return ui.getMessage();
    }

    /**
     * Sorts task based on nearest deadline.
     * @return Response string that contains sorted list of tasks.
     */
    private String sortTask() {
        Collections.sort(tasks);
        return listTask();
    }

    /**
     * Deletes task with given task number.
     * @param str Info about task number.
     * @return Response string for deleting task.
     */
    private String deleteTask(String[] str) {
        int k = Integer.parseInt(str[1]);

        ui.newMessage();
        ui.appendMessage("Noted. I've removed this task: \n");
        ui.appendMessage(String.format("%s\n", tasks.get(k - 1)));

        tasks.remove(k - 1);

        ui.appendMessage(String.format("Now you have %d tasks in the list.\n", tasks.size()));

        return ui.getMessage();
    }

    /**
     * Marks task with given task number as not done.
     * @param str Info about task number.
     * @return Response string for un-marking task.
     */
    private String unmarkTask(String[] str) {
        int k = Integer.parseInt(str[1]);
        tasks.get(k - 1).setAsUndone();

        ui.newMessage();
        ui.appendMessage("OK, I've marked this task as not done yet:\n");
        ui.appendMessage(String.format("%s\n", tasks.get(k - 1)));

        return ui.getMessage();
    }

    /**
     * Marks task with given task number as done.
     * @param str Info about task number.
     * @return Response string for marking task.
     */
    private String markTask(String[] str) {
        int k = Integer.parseInt(str[1]);
        tasks.get(k - 1).setAsDone();

        ui.newMessage();
        ui.appendMessage("Nice! I've marked this task as done: \n");
        ui.appendMessage(String.format("%s\n", tasks.get(k - 1)));

        return ui.getMessage();
    }

    /**
     * @return Response string of all tasks to be listed out.
     */
    private String listTask() {
        ui.newMessage();
        ui.appendMessage("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            ui.appendMessage(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return ui.getMessage();
    }

    /**
     * Gets welcome message on initialize.
     * @return Welcome message on initialize.
     */
    public String getWelcomeMessage() {
        return ui.welcome();
    }

    /**
     * Gets bye message on initialize.
     * @return Bye message on close.
     */
    public String getByeMessage() {
        return ui.bye();
    }

}
