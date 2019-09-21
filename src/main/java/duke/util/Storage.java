package duke.util;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks to hard drive.
 */
public class Storage {
    private File file;

    /**
     * Constructor for Storage.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads task from file.
     * @param list ArrayList to load tasks into.
     */
    public void load(TaskList list) {
        try {
            Scanner s = new Scanner(file);
            StringBuilder strBui = new StringBuilder();

            strBui.append("Loading tasks from " + file.getAbsolutePath() + "...");

            while (s.hasNextLine()) {
                String input = s.nextLine();
                try {
                    // split into task type and task attributes
                    String[] taskInput = input.split(" ", 2);
                    if (taskInput.length < 2) {
                        throw new DukeException("Invalid input: " + input);
                    }

                    String taskType = taskInput[0].strip().toUpperCase();
                    String taskAttr = taskInput[1].strip();

                    list.add(TaskType.valueOf(taskType).stringToTask(taskAttr));
                } catch (DukeException e) {
                    strBui.append("\n" + e.getMessage());
                } catch (IllegalArgumentException e) {
                    strBui.append("\nInvalid input: " + input);
                }
            }

            System.out.println(strBui.toString());
        } catch (FileNotFoundException e) {
            System.out.println("No tasks to load.");
        }
    }

    /**
     * Saves tasks to a text file.
     * @param list ArrayList to save tasks from.
     */
    public void save(TaskList list) {
        if (list.isEmpty()) {
            return;
        }

        try {
            if (!file.exists()) {
                System.out.println(file.getParentFile());
                boolean dirCreated = file.getParentFile().mkdir();
                if (dirCreated) {
                    file.createNewFile();
                } else {
                    System.out.println("Unable to create parent directories.");
                    return;
                }
            }

            System.out.println("Saving tasks to " + file.getAbsolutePath() + "...");

            FileWriter fw = new FileWriter(file);
            for (Task task : list.getTasks()) {
                fw.write(task.getType().taskToString(task));
            }
            fw.close();

        } catch (IOException | DukeException e) {
            System.out.println("Error writing to file.");
        }
    }
}
