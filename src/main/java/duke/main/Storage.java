package duke.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private String storagePath;

    public Storage(String path) {
        this.storagePath = path;
    }

    /**
     * Saves all task into backup file with path given in constructor.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If creating directory and file throws error.
     */
    public void saveTasksToStorage(TaskList tasks) throws IOException {
        if (Files.notExists(Paths.get(storagePath))) {
            Files.createDirectories(Paths.get("data/"));
            Files.createFile(Paths.get(storagePath));
        }
        FileWriter fw = new FileWriter(storagePath);
        for (Task task: tasks) {
            fw.write(task.convertToSaveFormat());
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Converts formatted string in backup file into a task instance..
     *
     * @param str Formatted string in backup file.
     * @return A task instance.
     */
    public Task loadTask(String[] str) {
        Task task = new Task();

        if (str[0].compareTo("[D]") == 0) {
            task = new Deadline(str[2], Parser.parseDate(str[3]));
        } else if (str[0].compareTo("[T]") == 0) {
            task = new Todo(str[2]);
        } else if (str[0].compareTo("[E]") == 0) {
            task = new Event(str[2], Parser.parseDate(str[3]));
        }
        if (str[1].equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads all task from backup file.
     *
     * @return List of tasks.
     * @throws FileNotFoundException If backup file cant be found.
     */
    public TaskList load() throws FileNotFoundException {
        File file = new File(storagePath);

        if (!file.exists()) {
            return new TaskList();
        } else {
            TaskList tasksList = new TaskList();
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String next = s.nextLine();

                String[] input = next.trim().split("\\s*\\|\\s*");

                Task task = loadTask(input);
                tasksList.add(task);
            }
            return tasksList;
        }
    }
}
