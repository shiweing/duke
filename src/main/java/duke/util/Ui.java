package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles logic input and output.
 */
public class Ui {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    /**
     * Gets input from user.
     */
    private Scanner scanner = new Scanner(System.in);

    public void greet() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return scanner.nextLine().strip();
    }

    public String formatError(String output) {
        return ANSI_RED + output + ANSI_RESET;
    }

    public String formatSuccess(String output) {
        return ANSI_GREEN + output + ANSI_RESET;
    }

    /**
     * Prints an alert after a task is added.
     * @param task duke.task.Task added.
     */
    public void printAddAlert(Task task, int listSize) {
        print(formatSuccess(String.format("Got it. I've added this task:%n" +
                "\t%s%n" +
                "Now you have %d tasks in the list.",
                task, listSize)));
    }

    public void printError(String output) {
        print(formatError(output));
    }

    /**
     * Prints in specified output format.
     * @param output Output to print.s
     */
    public void print(String output) {
        String border = "\t------------------------------------------------------------\n";
        output = output.replaceAll("(?m)^", "\t ");
        System.out.print(border + output + "\n" + border);
    }
}
