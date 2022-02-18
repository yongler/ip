package duke.main;

/**
 * Public UI class for I/O.
 */
public class Ui {
    private static String dividerLine = "---------------------------------";

    private StringBuilder message;

    public Ui() {
        message = new StringBuilder();
    }

    /**
     * Resets message to empty.
     */
    public void newMessage() {
        message = new StringBuilder();
    }

    /**
     * Appends string to exisitng message.
     * @param string Input string to be appended.
     */
    public void appendMessage(String string) {
        message.append(string);
    }

    public void appendNewLine() {
        message.append("\n");
    }

    /**
     * Gets message to be printed out.
     * @return String to be printed out.
     */
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
        message.append("Hello! I'm SUPERRRRDUKE\n");
        message.append("What can I do for you?\n");

        return getMessage();
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
}
