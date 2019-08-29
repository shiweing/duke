package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Error;

/**
 * Command that deletes tasks frm list.
 */
public class DeleteCommand extends Command {
    /**
     * Id of task to delete.
     */
    private int taskId;

    /**
     * Constructor for DeleteCommand.
     * @param attribute Task id of task.
     * @throws DukeException if task id is not an integer.
     */
    public DeleteCommand(String attribute) throws DukeException {
        try {
            taskId = Integer.parseInt(attribute.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Error.DELETE.getErrorString());
        }
    }

    /**
     * Execute deleting of task from list.
     * @param tasks TaskList to delete task from.
     * @param ui Ui for printing output.
     * @throws DukeException if task id larger than index in list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (taskId >= tasks.size()) {
            throw new DukeException(Error.DELETE.getErrorString());
        }

        Task task = tasks.delete(taskId);
        ui.print(String.format("Noted. I've removed this task:%n"
                        + "\t%s%n"
                        + "Now you have %d tasks in the list",
                task, tasks.size()));
    }

}
