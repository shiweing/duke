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
     * Constructor for Deadline.
     * @param desc Task description.
     * @param by Deadline of task.
     */
    public Deadline(String desc, Date by) {
        super(desc);
        this.by = by;
    }

    /**
     * Returns deadline of task.
     * @return Task deadline.
     */
    public Date getBy() {
        return this.by;
    }

    /**
     * Update deadline for task.
     * @param by New deadline to update.
     */
    public void setBy(Date by) {
        this.by = by;
    }

    /**
     * Returns deadline of task in String format "dd MMM yyyy hh:mm aa".
     * @return Task deadline in String
     */
    public String getByString() {
        return new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.by);
    }

    /**
     * Returns type of TaskType.DEADLINE.
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
