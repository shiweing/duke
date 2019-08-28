package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        // Exit
        ui.print("Bye. Hope to see you again soon!");
        Storage.save(tasks, ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
