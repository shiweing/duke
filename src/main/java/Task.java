/**
 * Represent a task.
 */
abstract class Task {
    /**
     * Task description.
     */
    private String desc;
    /**
     * Indicator for task completion.
     */
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param desc Task description.
     */
    Task(String desc) {
        this.desc = desc.strip();
        this.isDone = false;
    }

    /**
     * Another constructor for Task.
     * @param desc Task description.
     * @param isDone Indicator for completion of task.
     */
    Task(String desc, boolean isDone) {
        this.desc = desc.strip();
        this.isDone = isDone;
    }

    /**
     * Return task description.
     * @return Task description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Return if task is done.
     * @return True if done, false if not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets task as done.
     */
    public void done() {
        this.isDone = true;
    }

    abstract TaskType getType();

    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.isDone ? '\u2713' : '\u2717',
                this.desc);
    }
}
