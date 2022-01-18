import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
        ArrayList<String> arr = new ArrayList<>();

        while (true) {
            String str = sc.nextLine();
            if (str.compareTo("bye") == 0) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if (str.compareTo("list") == 0) {
                for (int i =0;i<arr.size();i++) {
                    System.out.printf("%d. %s\n",i+1, arr.get(i));
                }
            } else {
                arr.add(str);
                System.out.println("Added: " + str);
            }
        }


    }
}
