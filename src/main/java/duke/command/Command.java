package duke.command;

import duke.util.DukeException;
import duke.util.TaskList;

/**
 * Abstract command class.
 */
public abstract class Command {
    /**
     * Abstract method that child classes have to implement logic of command.
     * @param tasks TaskList to add task to.
     * @return Output of command.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
