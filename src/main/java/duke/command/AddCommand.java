package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.TaskType;

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
     * Executes adding of task to list.
     * @param tasks TaskList to add task to.
     * @throws DukeException from TaskType commandToTask().
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = TaskType.valueOf(taskType.toUpperCase()).commandToTask(attributes);
        boolean taskAdded = tasks.add(task);
        assert taskAdded == true: "Task not added successfully"; // assert task added

        return String.format("Got it. I've added this task:%n"
                        + "\t%s%n"
                        + "Now you have %d tasks in the list.",
                task, tasks.size());
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
