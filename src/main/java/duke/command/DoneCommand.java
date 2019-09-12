package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Error;
import duke.util.TaskList;

import java.util.ArrayList;

/**
 * Command to mark task as done.
 */
public class DoneCommand extends Command {
    /**
     * Ids of tasks to delete.
     */
    private ArrayList<Integer> taskIds;

    /**
     * Constructor for DoneCommand.
     * @param attribute Task id of task.
     * @throws DukeException if task id is not an integer.
     */
    public DoneCommand(String attribute) throws DukeException {
        taskIds = new ArrayList<>();
        // Split into multiple task id strings
        String[] taskIdStrs = attribute.strip().split(" ");

        for (String taskIdStr : taskIdStrs) {
            try {
                int taskId = Integer.parseInt(taskIdStr.strip());
                this.taskIds.add(taskId);

            } catch (NumberFormatException e) {
                throw new DukeException(Error.INVALID_TASK_NUMBER.getErrorString() + taskIdStr);
            }
        }
    }

    /**
     * Executes marking tasks as done.
     * @param tasks TaskList to obtain task from.
     * @return String result of command execution.
     */
    @Override
    public String execute(TaskList tasks) {
        StringBuilder strBui = new StringBuilder();
        StringBuilder invalidStrBui = new StringBuilder();

        for (int taskId : taskIds) {
            try {
                // add deleted task to string builder
                strBui.append("\n" + markTaskDone(tasks, taskId));
            } catch (DukeException e) {
                invalidStrBui.append("\n" + e.getMessage());
            }
        }

        if (strBui.length() == 0) {
            // if no tasks were successfully marked as done, display error
            strBui = invalidStrBui.deleteCharAt(0);
        } else {
            // if tasks were successfully marked as done
            strBui.insert(0, "Nice! I've marked these tasks as done:");

            if (invalidStrBui.length() != 0) {
                // if there were invalid tasks
                strBui.append("\n" + invalidStrBui);
            }
        }

        return strBui.toString();
    }

    /**
     * Mark task as done.
     * @param tasks Tasklist storing tasks.
     * @param taskId task ID of the task to mark as done.
     * @return String format of task marked as done.
     * @throws DukeException if taskId is not within Tasklist index.
     */
    private String markTaskDone(TaskList tasks, int taskId) throws DukeException {
        try {
            Task task = tasks.done(taskId);

            return String.format("\t%s", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Error.INVALID_TASK_NUMBER.getErrorString() + taskId);
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
