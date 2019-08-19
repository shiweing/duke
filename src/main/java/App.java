import java.util.Scanner;
import java.util.HashMap;

public class App {
    private HashMap<Integer, Task> list = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();
        // Echo
        while (!input.equals("bye")) {
            performAction(input);
            input = scanner.nextLine();
        }
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void performAction(String input) {
        try {
            String[] inputArr = input.split(" ", 2);
            //inputArr[0] is the action, inputArr[2] is the data
            switch (inputArr[0]) {
                // List
                case "list":
                    displayList();
                    break;
                case "done":
                    doneTask(inputArr);
                    break;
                case "todo":
                    addTodo(inputArr);
                    break;
                case "deadline":
                    addDeadline(inputArr);
                    break;
                case "event":
                    addEvent(inputArr);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    // List
    private void displayList() {
        if (list.isEmpty()) {
            System.out.println("You have no tasks in the list.");
        } else {
            for (int index : list.keySet()) {
                System.out.printf("%d. %s%n", index, list.get(index));
                index++;
            }
        }
    }
    // Done
    private void doneTask(String[] input) throws DukeException {
        try {
            int id = Integer.parseInt(input[1]);
            Task task = list.get(id);

            task.done();
            System.out.printf("Nice! I\'ve marked this task as done:%n%s%n", task);
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Please enter a valid task number.\n" +
                    "   Usage: done [task no.]");
        }
    }
    // Todo
    private void addTodo(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty())
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n" +
                    "   Usage: todo [task description]");

        Task task = new Todo(input[1]);
        list.put(list.size() + 1, task);
        printAlert(task);
    }
    // Deadline
    private void addDeadline(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty())
            throw new DukeException("OOPS!!! The description of a deadline task cannot be empty.\n" +
                    "   Usage: deadline [task description] /by [deadline]");

        String[] attributes = input[1].split(" /by ");

        if (attributes.length < 2 || attributes[1].strip().isEmpty())
            throw new DukeException("OOPS!!! Please provide a deadline for the task.\n" +
                    "   Usage: deadline [task description] /by [deadline]");

        Task task = new Deadline(attributes[0], attributes[1]);
        list.put(list.size() + 1, task);
        printAlert(task);
    }
    // Event
    private void addEvent(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().isEmpty())
            throw new DukeException("OOPS!!! The description of an event cannot be empty.\n" +
                    "   Usage: event [event description] /at [event time]");

        String[] attributes = input[1].split(" /at ");

        if (attributes.length < 2 || attributes[1].strip().isEmpty())
            throw new DukeException("OOPS!!! Please provide a time for the event.\n" +
                    "   Usage: event [event description] /at [event time]");

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
