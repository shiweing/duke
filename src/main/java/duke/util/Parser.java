package duke.util;

import duke.command.*;

public class Parser {
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
                case "find":
                    return new FindCommand(inputArr[1]);
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
