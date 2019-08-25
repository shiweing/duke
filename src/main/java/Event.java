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

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                new SimpleDateFormat("dd MMM yyyy hh:mm aa").format(this.at));
    }
}
