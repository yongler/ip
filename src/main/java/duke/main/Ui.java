package duke.main;

public class Ui {
    public Ui() {
    }

    /**
     * Prints welcome message.
     */
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

    /**
     * Prints bye message.
     */      
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints divider line for aesthetics purposes.
     * @return A dashed line.
     */
    public String dividerLine() {
        return ("---------------------------------");
    }

    /**
     * Prints loading error if there are no backup files to laod from.
     */
    public void showLoadingError() {
        System.out.println("No backup files to load from.");
    }
}
