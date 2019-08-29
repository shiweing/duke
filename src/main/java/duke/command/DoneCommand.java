package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Error;

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
     * Execute marking task as done.
     * @param tasks TaskList to obtain task from.
     * @param ui Ui for printing output.
     * @throws DukeException if task id larger than index in list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (taskId >= tasks.size()) {
            throw new DukeException(Error.DONE.getErrorString());
        }

        try {
            Task task = tasks.done(taskId);
            ui.print(String.format("Nice! I\'ve marked this task as done:%n   %s", task));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Error.DONE.getErrorString());
        }
    }
}
