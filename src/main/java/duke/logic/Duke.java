package duke.logic;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Main class of project.
 */
public class Duke  {
    private TaskList tasks;
    private Storage storage;
    private boolean isExit;

    /**
     * Constructor for duke.util.Duke.
     */
    public Duke(String file) {
        tasks = new TaskList();
        storage = new Storage(file);

        // Load data if exist
        storage.load(tasks);
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks);
            isExit = c.isExit();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public void bye() {
        storage.save(tasks);
    }
}
