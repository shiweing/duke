package duke.command;

import duke.task.Task;

import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.isEmpty()) {
            ui.print("You have no tasks in the list.");
        } else {
            int index = 1;
            StringBuilder strbui = new StringBuilder();

            for (Task task : tasks.getTasks()) {
                strbui.append(String.format("%d. %s%n", index, task));
                index++;
            }

            ui.print(strbui.deleteCharAt(strbui.length() - 1).toString());
        }
    }
}
