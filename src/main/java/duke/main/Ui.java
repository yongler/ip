package duke.main;

public class Ui {
    private StringBuilder message;

    private static String dividerLine = "---------------------------------";

    public Ui() {
        message = new StringBuilder();
    }

    public void newMessage() {
        message = new StringBuilder();
    }

    public void appendMessage(String string) {
        message.append(string);
    }

    public void appendNewLine() {
        message.append("\n");
    }

    public void appendDividerLine() {
        message.append(dividerLine);
    }

    public String getMessage() {
        return message.toString();
    }

    /**
     * Prints welcome message.
     */
    public String welcome() {
        newMessage();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        message.append("Hello from\n").append(logo);
        message.append("Hello from\n").append(logo);
        message.append("Hello! I'm SUPERRRRDUKE\n");
        message.append("What can I do for you?\n");

        return message.toString();
    }

    /**
     * Returns bye message.
     * @return Returns bye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints divider line for aesthetics purposes.
     * @return A dashed line.
     */
    public String dividerLine() {
        return dividerLine;
    }

    /**
     * Prints loading error if there are no backup files to laod from.
     */
    public String showLoadingError() {
        return("No backup files to load from.");
    }
}
