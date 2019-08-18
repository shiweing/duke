public class Event extends Task {
    private String at;

    Event(String desc, String at) {
        super(desc);
        this.at = at.strip();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
