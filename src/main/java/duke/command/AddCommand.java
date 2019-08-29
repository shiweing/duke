package duke.command;

import duke.task.Task;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.TaskType;
import duke.util.Ui;

/**
 * Command that adds task to the list.
 */
public class AddCommand extends Command {
    String taskType;
    String attributes;

    /**
     * Constructor for AddCommand.
     * @param taskType Type of task to add.
     * @param attributes Attributes of task.
     */
    public AddCommand(String taskType, String attributes) {
        this.taskType = taskType;
        this.attributes = attributes;
    }

    /**
     * Execute adding of task to list.
     * @param tasks TaskList to add task to.
     * @param ui Ui for printing output.
     * @throws DukeException from TaskType commandToTask().
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = TaskType.valueOf(taskType.toUpperCase()).commandToTask(attributes);
        tasks.add(task);

        ui.printAddAlert(task, tasks.size());
    }
}
