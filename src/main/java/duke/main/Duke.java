package duke.main;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Duke {
    private final Storage storage;
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
     * Returns the response associated with input by user to be displayed on GUI.
     * @param input String input from user.
     * @return String to be displayed on GUI.
     */
    public String getResponse(String input) {
        String[] str = Parser.splitInputStringBySpaces(input);
        String response = "";

        if (str[0].compareTo("bye") == 0) {
            response = ui.bye();
        } else if (str[0].compareTo("list") == 0) {
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
            Collections.sort(tasks);
            response = listTask();
        } else {
            try {
                response = addTask(str);
            } catch (DukeException e) {
                response = e.toString();
            }
        }

        try {
            storage.saveTasksToStorage(tasks);
        } catch (Exception e) {
            e.printStackTrace();
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
            throw new DukeException("todo");
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
            throw new DukeException("deadline");
        }

        StringBuilder stuff = new StringBuilder();
        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < str.length; i++) {
            if (str[i].compareTo("/by") == 0) {
                for (int j = 1; j < i; j++) {
                    stuff.append(str[j]).append(" ");

                }
                for (int j = i + 1; j < str.length; j++) {
                    deadline.append(str[j]).append(" ");
                }
                break;
            }
        }
        tasks.add(new Deadline(stuff.toString(), Parser.parseDate(deadline.toString())));
    }

    /**
     * Adds a new Event to tasks.
     *
     * @param str The whole input string, split.
     * @throws DukeException if no details when adding.
     */
    private void addEvent(String[] str) throws DukeException {

        if (str.length == 1) {
            throw new DukeException("event");
        }

        StringBuilder stuff = new StringBuilder();
        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < str.length; i++) {
            if (str[i].compareTo("/at") == 0) {
                for (int j = 1; j < i; j++) {
                    stuff.append(str[j]).append(" ");
                }
                for (int j = i + 1; j < str.length; j++) {
                    deadline.append(str[j]).append(" ");
                }
                break;
            }
        }
        tasks.add(new Event(stuff.toString(), Parser.parseDate(deadline.toString())));
    }

    /**
     * Adds a new task depending on type and append in to tasks.
     *
     * @param str The whole input string, split.
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
            throw new DukeException("tasks");
        }

        ui.newMessage();
        ui.appendMessage("Got it. I've added this task: \n");
        ui.appendMessage(String.format("%s\n",tasks.get(tasks.size() - 1)));
        ui.appendMessage(String.format("Now you have %d tasks in the list.\n", tasks.size()));

        return ui.getMessage();
    }

    /**
     * Find task given task number.
     * @param str Info about task number.
     */
    private String findTask(String[] str) {
        String toFind = str[1];

        ui.newMessage();
        ui.appendMessage("Here are the matching tasks in your list:\n");

        for (int i =0;i <tasks.size();i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(toFind)) {
                ui.appendMessage(String.format("%d %s\n", i+1, task));
            }
        }
        return ui.getMessage();
    }

    /**
     * Delete task.
     * @param str Info about task number.
     */
    private String deleteTask(String[] str) {
        int k = Integer.parseInt(str[1]);

        ui.newMessage();
        ui.appendMessage("Noted. I've removed this task: \n");
        ui.appendMessage(tasks.get(tasks.size() - 1).toString());
        ui.appendNewLine();

        tasks.remove(k - 1);

        ui.appendMessage(String.format("Now you have %d tasks in the list.\n", tasks.size()));

        return ui.getMessage();
    }

    /**
     * Marks task as not done.
     * @param str Info about task number.
     */
    private String unmarkTask(String[] str) {
        int k = Integer.parseInt(str[1]);
        tasks.get(k - 1).setAsUndone();

        ui.newMessage();
        ui.appendMessage("OK, I've marked this task as not done yet:\n");
        ui.appendMessage(String.format("%s\n",tasks.get(k - 1)));

        return ui.getMessage();
    }

    /**
     * Marks task as done.
     * @param str Info about task number.
     */
    private String markTask(String[] str) {
        int k = Integer.parseInt(str[1]);
        tasks.get(k - 1).setAsDone();

        ui.newMessage();
        ui.appendMessage("Nice! I've marked this task as done: \n");
        ui.appendMessage(String.format("%s\n",tasks.get(k - 1)));

        return ui.getMessage();
    }

    /**
     * Prints out all tasks.
     */
    private String listTask() {
        ui.newMessage();
        ui.appendMessage("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            ui.appendMessage(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return ui.getMessage();
    }

}
