import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks to hard drive
 */
public class Storage {
    public static File file = new File("data\\duke.txt");

    /**
     * Loads task from file.
     * @param list ArrayList to load tasks into.
     */
    public static void load(TaskList list, Ui ui) throws DukeException {
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
                    if (attributes.length < 2 ||
                            (!attributes[1].strip().equals("true") && !attributes[1].strip().equals("false"))) {
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

            ui.print(strBui.toString());
        } catch (FileNotFoundException e) {
            throw new DukeException("File no found: " + file.getAbsolutePath());
        }
    }

    /**
     * Save tasks to a text file.
     * @param list ArrayList to save tasks from.
     */
    public static void save(TaskList list, Ui ui) throws DukeException {
        if (list.isEmpty()) {
            return;
        }

        try {
            ui.print("Saving tasks to " + file.getAbsolutePath() + "...");
            file.getParentFile().mkdirs();
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            for (Task task : list.getTasks()) {
                fw.write(task.getType().taskToString(task));
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Error writing to file.");
        }
    }
}
