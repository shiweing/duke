import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum TaskType {
    TODO, DEADLINE, EVENT;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Checks input for each task type.
     * @param input user input of attributes
     * @return array of attributes
     * @throws DukeException if check fails
     */
    private String[] checkAndSplitInput(String input) throws DukeException {
        String[] attributes = input.split(" \\| ");

        switch (this) {
        case TODO:
            if (attributes.length < 2 || attributes[0].strip().isEmpty()
                    || (!attributes[1].equals("true") && !attributes[1].equals("false"))) {
                throw new DukeException("Invalid input: " + input);
            }
            break;

        case DEADLINE:
        case EVENT:
            if (attributes.length < 3 || attributes[0].strip().isEmpty()
                    || (!attributes[1].equals("true") && !attributes[1].equals("false"))
                    || attributes[2].strip().isEmpty()) {
                throw new DukeException("Invalid input: " + input);
            }
            break;

        default:
            throw new DukeException("Unknown type: " + this);
        }

        return attributes;
    }

    /**
     * Returns String output of tasks in format to save in file.
     * @param task Task to output string
     * @return String of format [Type] | [Desc] | [isDone] | [other attribute]
     * @throws DukeException If unknown type
     */
    String taskToString(Task task) throws DukeException {
        switch (this) {
        case TODO:
            Todo todo = (Todo) task;
            return String.format("%s | %s | %b\n", this, todo.getDesc(), todo.isDone());
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return String.format("%s | %s | %b | %s\n", this, deadline.getDesc(), deadline.isDone(), dateFormat.format(deadline.getBy()));
        case EVENT:
            Event event = (Event) task;
            return String.format("%s | %s | %b | %s\n", this, event.getDesc(), event.isDone(), dateFormat.format(event.getAt()));
        default:
            throw new DukeException("Unknown type: " + this);
        }
    }

    /**
     * Returns created task from String input.
     * @param input attributes from user input of format [Desc] | [isDone] | [other attribute]
     * @return Created task
     * @throws DukeException If input check fails
     */
    Task stringToTask(String input) throws DukeException {
        String[] attributes = checkAndSplitInput(input);
        try {
            switch (this) {
                case TODO:
                    return new Todo(attributes[0], Boolean.parseBoolean(attributes[1]));
                case DEADLINE:
                    Date deadline = dateFormat.parse(attributes[2]);
                    return new Deadline(attributes[0], Boolean.parseBoolean(attributes[1]), deadline);
                case EVENT:
                    Date eventTime = dateFormat.parse(attributes[2]);
                    return new Event(attributes[0], Boolean.parseBoolean(attributes[1]), eventTime);
                default:
                    throw new DukeException("Unknown type: " + this);
            }
        } catch (ParseException e) {
            throw new DukeException("Invalid input: " + input);
        }
    }
}
