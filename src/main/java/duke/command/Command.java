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

    /**
     * Return if application should exit.
     * @return false as default.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Return if tasks should be saved to txt file.
     * @return false as default.
     */
    public boolean requireSave() {
        return false;
    }
}
