package duke.task;

import duke.util.TaskType;

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
    public Event(String desc, Date at) {
        super(desc);
        this.at = at;
    }

    /**
     * Return event timing.
     * @return Event timing.
     */
    public Date getAt() {
        return this.at;
    }

    /**
     * Returns event timing in String format "dd MMM yyyy hh:mm aa".
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
        return String.format("[E]%s (at: %s)", super.toString(), getAtString());
    }
}
