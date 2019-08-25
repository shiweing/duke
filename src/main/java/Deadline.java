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

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.by));
    }
}
