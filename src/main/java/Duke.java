import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.IOException;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    protected static ArrayList<Task> tasks;

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }
        public void markAsUndone() {
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

//        public String getSymbol() {
//            return ;
//        }

        @Override
        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), description);
        }

        public String convertToSaveFormat() {
            return String.format("%s | %s | %s", " ", getStatusIcon(), description);
        }
    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + this.by + ")";
        }

        @Override
        public String convertToSaveFormat() {
            return String.format("%s | %s", super.convertToSaveFormat(), by);
        }
    }

    public static class Todo extends Task {
        protected String by;

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        @Override
        public String convertToSaveFormat() {
            return String.format("%s", super.convertToSaveFormat());
        }
    }

    public static class Event extends Task {
        protected String by;

        public Event(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(by: " + by + ")";
        }

        @Override
        public String convertToSaveFormat() {
            return String.format("%s | %s", super.convertToSaveFormat(), by);
        }
    }

    private static class DukeException extends Exception {
        public String toString() {
            return "";
        }
    }

    public static class TaskException extends DukeException {
        public String toString() {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
    public static class TodoException extends TaskException {
        @Override
        public String toString() {return "☹ OOPS!!! The description of a todo cannot be empty.";}
    }
    public static class DeadlineException extends TaskException {
        @Override
        public String toString() {return "☹ OOPS!!! The description of a deadline cannot be empty.";}
    }
    public static class EventException extends TaskException {
        @Override
        public String toString() {return "☹ OOPS!!! The description of a event cannot be empty.";}
    }

    public static void addTask(String whole_str, String[] str) throws TaskException {
        if (str[0].compareTo("deadline") == 0) {
            if (str.length == 1) {
                throw new DeadlineException();
            }
            String stuff = "";
            String deadline = "";

            for (int i =1;i< str.length;i++) {
                if (str[i].compareTo("/by") == 0) {
                    for (int j =1;j<i;j++) {
                        stuff += str[j] + " ";
                    }
                    for (int j =i+1;j<str.length;j++) {
                        deadline += str[j] + " ";
                    }
                    break;
                }
            }
            tasks.add(new Deadline(stuff, deadline));
        } else if (str[0].compareTo("todo") == 0) {
            if (str.length == 1) {
                throw new TodoException();
            }

            String stuff = "";
            for (int i =1;i< str.length;i++) {
                stuff += str[i] + " ";
            }
            tasks.add(new Todo(stuff));
        } else if (str[0].compareTo("event") == 0) {
            if (str.length == 1) {
                throw new EventException();
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
            tasks.add(new Event(stuff, deadline));
        } else {
            throw new TaskException();
        }

        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    private static class StorageException extends DukeException {
        public String toString() {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    private static void saveTasksToStorage() throws Exception {
        String storagePath = "data/duke.txt";
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

    private static ArrayList<Task> loadTasksFromStorage() {
        String storagePath = "data/duke.txt";
        File file = new File(storagePath);

        if (!file.exists()) {
            return new ArrayList<Task>();
        } else {
            ArrayList<Task> tasksList = new ArrayList<>();
            try{
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String[] in = s.nextLine().split("|");
//                Task task = addTask(in[1], in[2]);
//                tasksList.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }

            return tasksList;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm NewDuke");
        System.out.println("What can I do for you?");

        tasks = loadTasksFromStorage();

        while (true) {
            System.out.println("-------------");
            String whole_str = sc.nextLine();
            String[] str = whole_str.split(" ");

            if (str[0].compareTo("bye") == 0) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (str[0].compareTo("list") == 0) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
            } else if (str[0].compareTo("mark") == 0) {
                int k = Integer.parseInt(str[1]);
                tasks.get(k - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(k - 1));
            } else if (str[0].compareTo("unmark") == 0) {
                int k = Integer.parseInt(str[1]);
                tasks.get(k - 1).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(k - 1));
            } else if (str[0].compareTo("delete") == 0) {
                int k = Integer.parseInt(str[1]);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(tasks.size()-1).toString());
                tasks.remove(k-1);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            } else {
                try {
                    addTask(whole_str, str);
                } catch (TaskException e) {
                    System.out.println(e.toString());
                }
            }
            try {
                saveTasksToStorage();
            } catch (Exception e) {
                System.out.println("hi");
                System.out.println(e.toString());
            }
        }


    }
}
