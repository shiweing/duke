package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Error;

public class DoneCommand extends Command {
    private int taskId;

    public DoneCommand(String attribute) throws DukeException {
        try {
            taskId = Integer.parseInt(attribute.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Error.DONE.getErrorString());
        }
    }
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
