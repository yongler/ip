package duke.main;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void addTask(String whole_str, String[] str) throws TaskException {
        if (str[0].compareTo("deadline") == 0) {
            if (str.length == 1) {
                throw new TaskException("deadline");
            }
            String stuff = "";
            String deadline = "";

            for (int i = 1; i < str.length; i++) {
                if (str[i].compareTo("/by") == 0) {
                    for (int j = 1; j < i; j++) {
                        stuff += str[j] + " ";
                    }
                    for (int j = i + 1; j < str.length; j++) {
                        deadline += str[j] + " ";
                    }
                    break;
                }
            }
            tasks.add(new Deadline(stuff, parser.parseDate(deadline)));
        } else if (str[0].compareTo("todo") == 0) {
            if (str.length == 1) {
                throw new TaskException("todo");
            }

            String stuff = "";
            for (int i = 1; i < str.length; i++) {
                stuff += str[i] + " ";
            }
            tasks.add(new Todo(stuff));
        } else if (str[0].compareTo("event") == 0) {
            if (str.length == 1) {
                throw new TaskException("event");
            }

            String stuff = "";
            String deadline = "";

            for (int i =1;i< str.length;i++) {
                if (str[i].compareTo("/at") == 0) {
                    for (int j =1;j<i;j++) {
                        stuff += str[j] + " ";
                    }
                    for (int j =i+1;j<str.length;j++) {
                        deadline += str[j] + " ";
                    }
                    break;
                }
            }
            tasks.add(new Event(stuff, parser.parseDate(deadline)));
        } else {
            throw new TaskException("tasks");
        }

        System.out.println("Got it. I've added this duke.task: ");
        System.out.println(tasks.get(tasks.size()-1).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(ui.dividerLine());
            String whole_str = sc.nextLine();
            String[] str = whole_str.split(" ");

            if (str[0].compareTo("bye") == 0) {
                ui.bye();
                break;
            } else if (str[0].compareTo("list") == 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
            } else if (str[0].compareTo("mark") == 0) {
                int k = Integer.parseInt(str[1]);
                tasks.get(k - 1).markAsDone();
                System.out.println("Nice! I've marked this duke.task as done: ");
                System.out.println(tasks.get(k - 1));
            } else if (str[0].compareTo("unmark") == 0) {
                int k = Integer.parseInt(str[1]);
                tasks.get(k - 1).markAsUndone();
                System.out.println("OK, I've marked this duke.task as not done yet:");
                System.out.println(tasks.get(k - 1));
            } else if (str[0].compareTo("delete") == 0) {
                int k = Integer.parseInt(str[1]);
                System.out.println("Noted. I've removed this duke.task: ");
                System.out.println(tasks.get(tasks.size()-1).toString());
                tasks.remove(k-1);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            } else {
                try {
                    addTask(whole_str, str);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            }

            try {
                storage.saveTasksToStorage(tasks);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }


}
