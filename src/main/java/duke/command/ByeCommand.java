package duke.command;

import duke.util.DukeException;
import duke.util.TaskList;

/**
 * Command to exit application.
 */
public class ByeCommand extends Command {
    /**
     * Executes exit command and saving leftover tasks to hard drive.
     * @param tasks TaskList to add task to.
     * @throws DukeException from Storage save().
     */
    public String execute(TaskList tasks) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Overrides isExit() from Command parent class.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
