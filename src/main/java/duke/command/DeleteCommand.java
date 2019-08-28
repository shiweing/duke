package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Error;

public class DeleteCommand extends Command {
    private int taskId;

    public DeleteCommand(String attribute) throws DukeException {
        try {
            taskId = Integer.parseInt(attribute.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(Error.DELETE.getErrorString());
        }
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (taskId >= tasks.size()) {
            throw new DukeException(Error.DELETE.getErrorString());
        }

        Task task = tasks.delete(taskId);
        ui.print(String.format("Noted. I've removed this task:%n" +
                        "\t%s%n" +
                        "Now you have %d tasks in the list",
                task, tasks.size()));
    }

}
