package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to exit application.
 */
public class ByeCommand extends Command {
    /**
     * Execute exit command and saving leftover tasks to hard drive.
     * @param tasks TaskList to add task to.
     * @param ui Ui for printing output.
     * @throws DukeException from Storage save().
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        // Exit
        ui.print("Bye. Hope to see you again soon!");
        Storage.save(tasks, ui);
    }

    /**
     * Overrides isExit() from Command parent class.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
