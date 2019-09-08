package duke.logic;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.util.DukeException;
import duke.util.Error;

/**
 * Handles user command.
 */
public class Parser {
    /**
     * Make sense of user input and create Commands.
     * @param input User input.
     * @return Command object from user command.
     * @throws DukeException if user command is invalid.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.strip().split(" ", 2);
        String command = inputArr[0];

        if (inputArr.length == 1) {
            switch (command) {
            // List
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            default:
                try {
                    // Throw exception based on command that require attributes (done, delete etc.)
                    String errorType = command.strip().toUpperCase();
                    throw new DukeException(Error.valueOf(errorType).getErrorString());
                } catch (IllegalArgumentException e) {
                    // Throw exception for invalid command string
                    throw new DukeException(Error.DEFAULT.getErrorString());
                }
            }
        } else {
            String commandAttr = inputArr[1];

            switch (command) {
            case "done":
                return new DoneCommand(commandAttr);
            case "delete":
                return new DeleteCommand(commandAttr);
            case "find":
                return new FindCommand(commandAttr);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(command, commandAttr);
            default:
                // Throw exception for invalid command string
                throw new DukeException(Error.DEFAULT.getErrorString());
            }
        }
    }
}
