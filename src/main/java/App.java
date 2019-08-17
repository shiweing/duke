import java.util.Scanner;
import java.util.HashMap;

public class App {
    private HashMap<Integer, Task> list = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = scanner.next();
        // Echo
        while (!input.equals("bye")) {
            performAction(input);
            input = scanner.next();
        }
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void performAction(String action) {
        switch (action) {
            // List
            case "list":
                displayList();
                break;
            case "done":
                doneTask(scanner.nextInt());
                break;
            default:
                addTask(action + scanner.nextLine());
                //scanner.next();
        }
    }
    // List
    private void displayList() {
        for (int index : list.keySet()) {
            System.out.printf("%d. %s%n", index, list.get(index));
            index++;
        }
    }
    // Done
    private void doneTask(int id) {
        Task task = list.get(id);
        task.done();
        System.out.printf("Nice! I\'ve marked this task as done:%n%s%n", task);
    }
    // Add
    private void addTask(String taskDesc) {
        System.out.println(taskDesc);
        Task task = new Task(taskDesc);
        list.put(list.size() + 1, task);
        System.out.println("added: " + taskDesc);
    }
}
