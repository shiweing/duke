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
     * Id of task to delete.
     */
//    private int taskId;
    private ArrayList<Integer> taskIds;
    /**
     * Constructor for DeleteCommand.
     * @param attribute Task id of task.
     * @throws DukeException if task id is not an integer.
     */
    public DeleteCommand(String attribute) throws DukeException {
        try {
            taskIds = new ArrayList<>();
            // Split into multiple task id strings
            String[] taskIdStrs = attribute.strip().split(" ");

            for (String taskIdStr : taskIdStrs) {
                System.out.println(taskIdStr);
                int taskId = Integer.parseInt(taskIdStr.strip()) - 1;
                this.taskIds.add(taskId);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(Error.DELETE.getErrorString());
        }
    }

    /**
     * Executes deleting of task from list.
     * @param tasks TaskList to delete task from.
     * @throws DukeException if task id larger than index in list.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {

        StringBuilder validStrBui = new StringBuilder();
        StringBuilder invalidStrBui = new StringBuilder();

        for (int taskId : taskIds) {
            /*if (taskId >= tasks.size()) {
                throw new DukeException(Error.DELETE.getErrorString() + taskId);
            }*/

            try {
                // add deleted task to string builder
                validStrBui.append(deleteTask(tasks, taskId));
            } catch (DukeException e) {
                invalidStrBui.append("\n" + e.getMessage());
            }
        }


        if (validStrBui.length() == 0) {
            // if no tasks were successfully deleted, display error
            validStrBui = invalidStrBui.deleteCharAt(0);
        } else {
            validStrBui.insert(0, "Noted. I've removed these tasks:\n");
            validStrBui.append(String.format("Now you have %d tasks in the list.", tasks.size()));

            validStrBui.append("\n" + invalidStrBui);
        }

        return validStrBui.toString();
    }

    private String deleteTask(TaskList tasks, int taskId) throws DukeException {
        try {
            Task task = tasks.delete(taskId);

            return String.format("\t%s%n", task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Error.INVALID_TASK_NUMBER.getErrorString() + (taskId+1));
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
