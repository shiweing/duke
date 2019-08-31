package duke.util;

/**
 * Represents an Exception thrown by the application.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns exception message.
     * @return exception message.
     */
    @Override
    public String getMessage() {
        return String.format("\u2639 %s", super.getMessage());
    }
}
