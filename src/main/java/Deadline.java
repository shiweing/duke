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
     * Constructor for Deadlne.
     * @param desc Task description.
     * @param by Deadline of task.
     */
    Deadline(String desc, Date by) {
        super(desc);
        this.by = by;
    }

    /**
     * Another constructor for Deadline.
     * @param desc Task description.
     * @param isDone Completion of task.
     * @param by Deadline of task.
     */
    Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = by.strip();
    }

    /**
     * Return deadline of task.
     * @return Task deadline.
     */
    public String getBy() {
        return this.by;
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
        return String.format("[D]%s (by: %s)", super.toString(),
                new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.by));
    }
}
