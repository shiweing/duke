package duke.task;

import duke.util.TaskType;

/**
 * Represents a task.
 */
public abstract class Task {
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
     * Returns task description.
     * @return Task description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns if task is done.
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

    /**
     * Abstract method child class have to implement which returns type of task.
     * @return TaskType
     */
    public abstract TaskType getType();

    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.isDone ? '\u2713' : '\u2717',
                this.desc);
    }
}
