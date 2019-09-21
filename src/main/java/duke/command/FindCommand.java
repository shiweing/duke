package duke.command;

import duke.task.Task;
import duke.util.TaskList;

/**
 * Command to find tasks related to keyword.
 */
public class FindCommand extends Command {
    /**
     * Keyword to match tasks to.
     */
    private String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword Keyword to match tasks to.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.strip();
    }

    /**
     * Executes find command to find tasks matching keywords.
     * @param tasks TaskList to find tasks from.
     */
    public String execute(TaskList tasks) {
        int index = 1;
        StringBuilder strbui = new StringBuilder();

        for (Task task : tasks.getTasks()) {
            if (task.getDesc().contains(keyword)) {
                strbui.append(String.format("%d. %s%n", index, task));
                index++;
            }
        }

        if (strbui.length() == 0) {
            strbui.append("There are no tasks matching your keyword.");
        } else {
            strbui.insert(0, "Here are the matching tasks in your list:\n");
        }

        return strbui.toString();
    }
}
