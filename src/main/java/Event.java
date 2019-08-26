import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represent an event.
 */
public class Event extends Task {
    /**
     * The event timing.
     */
    private Date at;

    /**
     * Constructor for Event.
     * @param desc Task description.
     * @param at Event timing.
     */
    Event(String desc, Date at) {
        super(desc);
        this.at = at;
    }

    /**
     * Another constructor for Event.
     * @param desc Task description.
     * @param isDone Completion of task.
     * @param at Event timing..
     */
    Event(String desc, boolean isDone, Date at) {
        super(desc, isDone);
        this.at = at;
    }

    public Date getAt() {
        return this.at;
    }

    /**
     * Returns event timing.
     * @return Event timing in String
     */
    public String getAtString() {
        return new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.at);
    }

    /**
     * Returns TaskType.EVENT.
     * @return Type EVENT.
     */
    public TaskType getType() {
        return TaskType.EVENT;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.at));
    }
}
