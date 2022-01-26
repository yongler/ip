package duke.main;

public class Ui {
    public Ui() {
    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm SUPERRRRDUKE");
        System.out.println("What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void dividerLine() {
        System.out.println("---------------------------------");
    }

    public void showLoadingError() {
        System.out.println("No backup files to load from.");
    }
}
