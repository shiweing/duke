package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;

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
        String[] inputArr = input.split(" ", 2);

        if (inputArr.length == 1) {
            switch (inputArr[0]) {
            // List
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            default:
                try {
                    throw new DukeException(Error.valueOf(inputArr[0].strip().toUpperCase()).getErrorString());
                } catch (IllegalArgumentException e) {
                    throw new DukeException(Error.DEFAULT.getErrorString());
                }
            }
        } else {
            switch (inputArr[0]) {
            case "done":
                return new DoneCommand(inputArr[1]);
            case "delete":
                return new DeleteCommand(inputArr[1]);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(inputArr[0], inputArr[1]);
            default:
                throw new DukeException(Error.DEFAULT.getErrorString());
            }
        }
    }
}
