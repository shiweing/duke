import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private ArrayList<Task> list = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        // Greet
        print("Hello! I'm Duke\nWhat can I do for you?");
        String input = scanner.nextLine().strip();
        // Echo
        while (!input.equals("bye")) {
            performAction(input);
            input = scanner.nextLine().strip();
        }
        // Exit
        print("Bye. Hope to see you again soon!");
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
                case "delete":
                    deleteTask(inputArr);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            print(e.getMessage());
        }
    }
    // List
    private void displayList() {
        if (list.isEmpty()) {
            print("You have no tasks in the list.");
        } else {
            int index = 1;
            StringBuilder strbui = new StringBuilder();

            for (Task task : list) {
                strbui.append(String.format("%d. %s%n", index, task));
                index++;
            }

            print(strbui.deleteCharAt(strbui.length() - 1).toString());
        }
    }
    // Done
    private void doneTask(String[] input) throws DukeException {
        try {
            int id = Integer.parseInt(input[1]) - 1;
            Task task = list.get(id);

            task.done();
            print(String.format("Nice! I\'ve marked this task as done:%n%s", task));
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
        list.add(task);
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
        list.add(task);
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
        list.add(task);
        printAlert(task);
    }
    // Delete
    private void deleteTask(String[] input) throws DukeException {
        try {
            int id = Integer.parseInt(input[1]) - 1;
            Task task = list.remove(id);
            print(String.format("Noted. I've removed this task:%n" +
                            "\t%s%n" +
                            "Now you have %d tasks in the list",
                    task, list.size()));
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Please enter a valid task number.\n" +
                    "   Usage: delete [task no.]");
        }
    }

    private void printAlert(Task task) {
        print(String.format("Got it. I've added this task:%n" +
                "\t%s%n" +
                "Now you have %d tasks in the list.",
                task, list.size()));
    }

    private void print(String output) {
        String border = "\t------------------------------------------------------------\n";
        output = output.replaceAll("(?m)^", "\t ");
        System.out.print(border + output + "\n" + border);
    }
}
