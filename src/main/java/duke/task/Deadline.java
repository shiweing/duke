package duke.task;

import duke.util.TaskType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * Stores deadline of task.
     */
    private Date by;

    /**
     * Constructor for duke.task.Deadline.
     * @param desc duke.task.Task description.
     * @param by duke.task.Deadline of task.
     */
    public Deadline(String desc, Date by) {
        super(desc);
        this.by = by;
    }

    public Date getBy() {
        return this.by;
    }

    /**
     * Return deadline of task.
     * @return duke.task.Task deadline in String
     */
    public String getByString() {
        return new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.by);
    }

    /**
     * Returns type of duke.util.TaskType.DEADLINE.
     * @return Type DEADLINE.
     */
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getByString());
    }
}
