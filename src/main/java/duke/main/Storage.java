package duke.main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.task.Task;

public class Storage {
    private String storagePath;

    public Storage(String path) {
        this.storagePath = path;
    }

    public void saveTasksToStorage(TaskList tasks) throws IOException {
        if (Files.notExists(Paths.get(storagePath))) {
            Files.createDirectories(Paths.get("data/"));
            Files.createFile(Paths.get(storagePath));
            System.out.println("create file");
        }
        FileWriter fw = new FileWriter(storagePath);
        for (Task task: tasks) {
            fw.write(task.convertToSaveFormat());
            fw.write("\n");
        }
        fw.close();
    }

    public TaskList load() throws FileNotFoundException {
        File file = new File(storagePath);

        if (!file.exists()) {
            return new TaskList();
        } else {
            TaskList tasksList = new TaskList();
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] in = s.nextLine().split("|");
//                Task duke.task = addTask(in[1], in[2]);
//                tasksList.add(duke.task);
            }

            return tasksList;
        }
    }
}
