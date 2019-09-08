package duke.command;

import duke.task.Task;
import duke.util.TaskList;

/**
 * Command to list tasks in list.
 */
public class ListCommand extends Command {
    /**
     * Executes listing of tasks.
     * @param tasks TaskList to obtain tasks from.
     */
    public String execute(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in the list.";
        }

        int index = 1;
        StringBuilder strbui = new StringBuilder();

        for (Task task : tasks.getTasks()) {
            strbui.append(String.format("%d. %s%n", index, task));
            index++;
        }

        return strbui.deleteCharAt(strbui.length() - 2).toString();
    }
}
