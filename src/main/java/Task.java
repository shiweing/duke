public class Task {
    private String desc;
    private boolean isDone;

    Task(String desc) {
        this.desc = desc.strip();
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone ? "\u2713" : "\u2717",
                this.desc);
    }
}
