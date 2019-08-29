package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Handles tasks creation from String and command and Task to String based on Task type.
 */
public enum TaskType {
    TODO, DEADLINE, EVENT;

    /**
     * Date formatter for deadline and event time.
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Checks input for each task type.
     * @param input user input of attributes.
     * @return array of attributes.
     * @throws DukeException if check fails.
     */
    private String[] checkAndSplitAttributes(String input) throws DukeException {
        ArrayList<String> attributes = new ArrayList<>();
        String[] attrArr;

        switch (this) {
        case TODO:
            if (input.strip().isEmpty()) {
                throw new DukeException(Error.TODO.getErrorString());
            }
            attributes.add(input.strip());
            break;

        case DEADLINE:
            if (input.strip().isEmpty()) {
                throw new DukeException(Error.DEADLINE.getErrorString());
            }
            // attrArr[0]: task desc | attrArr[1]: deadline
            attrArr = input.split(" /by ");
            if (attrArr.length < 2) {
                throw new DukeException(Error.DEADLINE_BY.getErrorString());
            }
            attributes.add(attrArr[0].strip());
            attributes.add(attrArr[1].strip());
            break;

        case EVENT:
            if (input.strip().isEmpty()) {
                throw new DukeException(Error.EVENT.getErrorString());
            }
            // attrArr[0]: task desc | attrArr[1]: eventTime
            attrArr = input.split(" /at ");
            if (attrArr.length < 2) {
                throw new DukeException(Error.EVENT_AT.getErrorString());
            }
            attributes.add(attrArr[0].strip());
            attributes.add(attrArr[1].strip());
            break;

        default:
            throw new DukeException("Unknown type: " + this);
        }

        return attributes.toArray(new String[attributes.size()]);
    }

    /**
     * Returns created task from String input.
     * @param input attributes from user input of format [Desc] [other attributes].
     * @return Created task.
     * @throws DukeException If input check fails.
     */
    public Task commandToTask(String input) throws DukeException {
        String[] attributes = checkAndSplitAttributes(input);

        switch (this) {
        case TODO:
            return new Todo(attributes[0]);
        case DEADLINE:
            try {
                Date deadline = dateFormat.parse(attributes[1]);
                return new Deadline(attributes[0], deadline);
            } catch (ParseException e) {
                throw new DukeException(Error.DEADLINE_FORMAT.getErrorString());
            }
        case EVENT:
            try {
                Date eventTime = dateFormat.parse(attributes[1]);
                return new Event(attributes[0], eventTime);
            } catch (ParseException e) {
                throw new DukeException(Error.EVENT_TIME_FORMAT.getErrorString());
            }
        default:
            throw new DukeException("Unknown type: " + this);
        }
    }

    public Task stringToTask(String input, boolean isDone) throws DukeException {
        Task task = commandToTask(input);
        if (isDone) {
            task.done();
        }

        return task;
    }

    /**
     * Returns String output of tasks in format to save in file.
     * @param task duke.task.Task to output string
     * @return String of format [Type] [Desc] [other attribute] [isDone]
     * @throws DukeException If unknown type
     */
    public String taskToString(Task task) throws DukeException {
        switch (this) {
        case TODO:
            Todo todo = (Todo) task;
            return String.format("%s %s /done %b\n", this.toString(), todo.getDesc(), todo.isDone());
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return String.format("%s %s /by %s /done %b\n",
                    this.toString(), deadline.getDesc(), dateFormat.format(deadline.getBy()), deadline.isDone());
        case EVENT:
            Event event = (Event) task;
            return String.format("%s %s /at %s /done %b\n",
                    this.toString(), event.getDesc(), dateFormat.format(event.getAt()), event.isDone());
        default:
            throw new DukeException("Unknown type: " + this);
        }
    }
}
