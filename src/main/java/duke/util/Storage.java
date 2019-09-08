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
                    String[] taskInput = input.split(" ", 2);
                    String taskType = taskInput[0].strip().toUpperCase();

                    String[] attributes = taskInput[1].split(" /done ");
                    if (attributes.length < 2
                            || (!attributes[1].strip().equals("true") && !attributes[1].strip().equals("false"))) {
                        throw new DukeException("Invalid input: " + input);
                    }

                    list.add(TaskType.valueOf(taskType)
                            .stringToTask(attributes[0], Boolean.parseBoolean(attributes[1].strip())));
                } catch (DukeException e) {
                    strBui.append("\n" + e.getMessage());
                } catch (IllegalArgumentException e) {
                    strBui.append("\nInvalid input: " + input);
                }
            }

            System.out.println(strBui.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
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
            System.out.println("Saving tasks to " + file.getAbsolutePath() + "...");

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

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
