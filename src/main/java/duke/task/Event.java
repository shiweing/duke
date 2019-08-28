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
     * Constructor for duke.task.Event.
     * @param desc duke.task.Task description.
     * @param at duke.task.Event timing.
     */
    public Event(String desc, Date at) {
        super(desc);
        this.at = at;
    }

    public Date getAt() {
        return this.at;
    }

    /**
     * Returns event timing.
     * @return duke.task.Event timing in String
     */
    public String getAtString() {
        return new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.at);
    }

    /**
     * Returns duke.util.TaskType.EVENT.
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
