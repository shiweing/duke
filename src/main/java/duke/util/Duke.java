package duke.util;

import duke.command.Command;

/**
 * Main class of project.
 */
public class Duke  {
    private TaskList tasks;
    private boolean isExit;

    /**
     * Constructor for duke.util.Duke.
     */
    public Duke() {
        tasks = new TaskList();

        // Load data if exist
        Storage.load(tasks);
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
        Storage.save(tasks);
    }
}
