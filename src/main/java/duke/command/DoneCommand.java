package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Error;
import duke.util.TaskList;

/**
 * Command to mark task as done.
 */
public class DoneCommand extends Command {
    /**
     * Id of task to delete.
     */
    private int taskId;

    /**
     * Constructor for DoneCommand.
     * @param attribute Task id of task.
     * @throws DukeException if task id is not an integer.
     */
    public DoneCommand(String attribute) throws DukeException {
        try {
            taskId = Integer.parseInt(attribute.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Error.DONE.getErrorString());
        }
    }

    /**
     * Executes marking task as done.
     * @param tasks TaskList to obtain task from.
     * @throws DukeException if task id larger than index in list.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (taskId >= tasks.size()) {
            throw new DukeException(Error.DONE.getErrorString());
        }

        try {
            Task task = tasks.done(taskId);
            return String.format("Nice! I\'ve marked this task as done:%n   %s", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Error.DONE.getErrorString());
        }
    }

    /**
     * Returns true for Duke to save tasks to txt file.
     * @return true.
     */
    @Override
    public boolean requireSave() {
        return true;
    }
}
