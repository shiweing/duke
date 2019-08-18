public class Deadline extends Task {
    private String by;

    Deadline(String desc, String by) {
        super(desc);
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
