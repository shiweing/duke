package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.TaskType;
import duke.util.Ui;

public class AddCommand extends Command {
    String taskType;
    String attributes;

    public AddCommand(String taskType, String attributes) {
        this.taskType = taskType;
        this.attributes = attributes;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = TaskType.valueOf(taskType.toUpperCase()).commandToTask(attributes);
        tasks.add(task);

        ui.printAddAlert(task, tasks.size());
    }
}
