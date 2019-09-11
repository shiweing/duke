package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Error;
import duke.util.TaskList;

import java.util.ArrayList;

/**
 * Command that deletes tasks frm list.
 */
public class DeleteCommand extends Command {
    /**
     * Ids of tasks to delete.
     */
    private ArrayList<Integer> taskIds;

    /**
     * Constructor for DeleteCommand.
     * @param attribute Task id of task.
     * @throws DukeException if task id is not an integer.
     */
    public DeleteCommand(String attribute) throws DukeException {
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
     * Executes deleting of task from list.
     * @param tasks TaskList to delete task from.
     * @throws DukeException if task id larger than index in list.
     */
    @Override
    public String execute(TaskList tasks) {
        StringBuilder strBui = new StringBuilder();
        StringBuilder invalidStrBui = new StringBuilder();

        for (int taskId : taskIds) {
            try {
                // add deleted task to string builder
                strBui.append("\n" + deleteTask(tasks, taskId));
            } catch (DukeException e) {
                invalidStrBui.append("\n" + e.getMessage());
            }
        }

        if (strBui.length() == 0) {
            // if no tasks were successfully deleted, display error
            strBui = invalidStrBui.deleteCharAt(0);
        } else {
            // if tasks have been deleted
            strBui.insert(0, "Noted. I've removed these tasks:");
            strBui.append(String.format("Now you have %d tasks in the list.", tasks.size()));

            if (invalidStrBui.length() != 0) {
                // if there were invalid tasks
                strBui.append("\n" + invalidStrBui);
            }
        }

        return strBui.toString();
    }

    private String deleteTask(TaskList tasks, int taskId) throws DukeException {
        try {
            Task task = tasks.delete(taskId);

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
