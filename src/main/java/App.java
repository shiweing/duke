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
            case "todo":
                addTodo(scanner.nextLine());
                break;
            case "deadline":
                addDeadline(scanner.nextLine());
                break;
            case "event":
                addEvent(scanner.nextLine());
                break;
            default:
                addTask(action + scanner.nextLine());
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
        Task task = new Task(taskDesc);
        list.put(list.size() + 1, task);
        System.out.println("added: " + taskDesc);
    }
    // Todo
    private void addTodo(String taskDesc) {
        Task task = new Todo(taskDesc);
        list.put(list.size() + 1, task);
        printAlert(task);
    }
    // Deadline
    private void addDeadline(String input) {
        String[] attributes = input.split("/by");
        Task task = new Deadline(attributes[0], attributes[1]);
        list.put(list.size() + 1, task);
        printAlert(task);
    }
    // Event
    private void addEvent(String input) {
        String[] attributes = input.split("/at");
        Task task = new Event(attributes[0], attributes[1]);
        list.put(list.size() + 1, task);
        printAlert(task);
    }

    private void printAlert(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
    }
}
