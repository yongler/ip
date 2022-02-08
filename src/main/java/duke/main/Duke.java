package duke.main;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Create duke object.
     * @param filePath Specify storage file path.
     */
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

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Adds a new task depending on type and append in to tasks.
     *
     * @param str       the whole input string, split.
     * @throws TaskException if no details when adding.
     */
    private void addTask(String[] str) throws TaskException {
        if (str[0].compareTo("deadline") == 0) {
            if (str.length == 1) {
                throw new TaskException("deadline");
            }

            StringBuilder stuff = new StringBuilder();
            StringBuilder deadline = new StringBuilder();

            for (int i = 1; i < str.length; i++) {
                if (str[i].compareTo("/at") == 0) {
                    for (int j = 1; j < i; j++) {
                        stuff.append(str[j]).append(" ");

                    }
                    for (int j = i + 1; j < str.length; j++) {
                        deadline.append(str[j]).append(" ");
                    }
                    break;
                }
            }
            tasks.add(new Deadline(stuff.toString(), Parser.parseDate(deadline.toString())));
        } else if (str[0].compareTo("todo") == 0) {
            if (str.length == 1) {
                throw new TaskException("todo");
            }

            StringBuilder stuff = new StringBuilder();
            for (int i = 1; i < str.length; i++) {
                stuff.append(str[i]).append(" ");
            }
            tasks.add(new Todo(stuff.toString()));
        } else if (str[0].compareTo("event") == 0) {
            if (str.length == 1) {
                throw new TaskException("event");
            }

            StringBuilder stuff = new StringBuilder();
            StringBuilder deadline = new StringBuilder();

            for (int i = 1; i < str.length; i++) {
                if (str[i].compareTo("/at") == 0) {
                    for (int j = 1; j < i; j++) {
                        stuff.append(str[j]).append(" ");
                    }
                    for (int j = i + 1; j < str.length; j++) {
                        deadline.append(str[j]).append(" ");
                    }
                    break;
                }
            }
            tasks.add(new Event(stuff.toString(), Parser.parseDate(deadline.toString())));
        } else {
            throw new TaskException("tasks");
        }

        System.out.println("Got it. I've added this duke.task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Runs the whole Duke program and processes input
     * until the 'bye' command is given.
     */
    public void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);

        while (true) {
            assert false : "FALSE";

            System.out.println(ui.dividerLine());
            String wholeString = sc.nextLine();
            String[] str = wholeString.split(" ");

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
                tasks.get(k - 1).setAsDone();
                System.out.println("Nice! I've marked this duke.task as done: ");
                System.out.println(tasks.get(k - 1));
            } else if (str[0].compareTo("unmark") == 0) {
                int k = Integer.parseInt(str[1]);
                tasks.get(k - 1).setAsUndone();
                System.out.println("OK, I've marked this duke.task as not done yet:");
                System.out.println(tasks.get(k - 1));
            } else if (str[0].compareTo("delete") == 0) {
                int k = Integer.parseInt(str[1]);
                System.out.println("Noted. I've removed this duke.task: ");
                System.out.println(tasks.get(tasks.size() - 1).toString());
                tasks.remove(k - 1);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            } else if (str[0].compareTo("find") == 0) {
                String toFind = str[1];

                int count = 1;
                System.out.println("Here are the matching tasks in your list:");
                for (Task task : tasks) {
                    if (task.getDescription().contains(toFind)) {
                        System.out.printf("%d. %s\n", count, task);
                    }
                }
            } else {
                try {
                    addTask(str);
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }

            try {
                storage.saveTasksToStorage(tasks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
