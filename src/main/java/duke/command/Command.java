package duke.command;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Abstract command class.
 */
public abstract class Command {
    /**
     * Abstract method that child classes have to implement logic of command.
     * @param tasks TaskList to add task to.
     * @param ui Ui for printing output.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
    public boolean isExit() {
        return false;
    }
}
