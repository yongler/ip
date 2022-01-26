package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private String storagePath;

    public Storage(String path) {
        this.storagePath = path;
    }

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
            task.setAsDone();
        }
        return task;
    }

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
