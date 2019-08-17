import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList();
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();
        // Echo
        while (!input.equals("bye")) {
            if(input.equals("list")) {
                // List
                displayList(list);
            }
            else {
                // Add
                list.add(input);
                System.out.println("added: " + input);
            }

            input = scanner.nextLine();
        }
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void displayList(ArrayList<String> list) {
        int index = 1;

        for (String task : list) {
            System.out.printf("%d. %s%n", index, task);
            index++;
        }
    }
}
