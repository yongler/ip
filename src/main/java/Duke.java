import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void toggleDone() {
            this.isDone = !this.isDone;
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

        @Override
        public String toString() {
            return String.format("[%s] %s", getStatusIcon(), description);
        }
    }

//    public class Deadline extends Task {
//
//        protected String by;
//
//        public Deadline(String description, String by) {
//            super(description);
//            this.by = by;
//        }
//
//        @Override
//        public String toString() {
//            return "[D]" + super.toString() + " (by: " + by + ")";
//        }
//    }

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

        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
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

            } else {
                tasks.add(new Task(whole_str));
                System.out.println("Added: " + whole_str);
            }
        }


    }
}
