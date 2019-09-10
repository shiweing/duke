package duke.util;

/**
 * Error Enums.
 */
public enum Error {
    DONE("OOPS!!! Please enter a valid task number.\n"
            + "   Usage: done [task no.]"),
    DELETE("OOPS!!! Please enter a valid task number.\n"
            + "   Usage: delete [task no.]"),
    FIND("OOPS!! Please enter a valid keyword.\n"
            + "   Usage: find [keyword]"),
    TODO("OOPS!!! The description of a todo cannot be empty.\n"
            + "   Usage: todo [task description]"),
    DEADLINE("OOPS!!! The description of a deadline task cannot be empty.\n"
            + "   Usage: deadline [task description] --by [deadline]"),
    DEADLINE_BY("OOPS!!! Please provide a deadline for the task.\n"
            + "   Usage: deadline [task description] --by [deadline]"),
    DEADLINE_FORMAT("Oh no! Please ensure the deadline in the following format:\n"
            + "   dd/MM/yyyy HHmm"),
    EVENT("OOPS!!! The description of an event cannot be empty.\n"
            + "   Usage: event [event description] --at [event time]"),
    EVENT_TIME_FORMAT("Oh no! Please ensure the event time in the following format:\n"
            + "   dd/MM/yyyy HHmm"),
    EVENT_AT("OOPS!!! Please provide a time for the event.\n"
            + "   Usage: event [event description] --at [event time]"),
    UPDATE("OOPS!!! Please enter a valid task number.\n"
            + "   Usage: update [task id] {--desc [task description]} {--by [deadline]} {--at [event time]} "),
    UPDATE_INVALID_ATTRIBUTES("OOPS!!! Please provide a valid attribute to update.\n"
            + "   Usage: update [task id] {--desc [task description]} {--by [deadline]} {--at [event time]} "),
    DEFAULT("OOPS!!! I'm sorry, but I don't know what that means :-(");

    /**
     * Error string for each error.
     */
    private String errorString;

    /**
     * Constructor of Error.
     * @param errorString Error message for error type.
     */
    Error(String errorString) {
        this.errorString = errorString;
    }

    /**
     * Returns error string by enum.
     * @return
     */
    public String getErrorString() {
        return this.errorString;
    }
}
