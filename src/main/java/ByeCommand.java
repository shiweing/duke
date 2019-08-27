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
