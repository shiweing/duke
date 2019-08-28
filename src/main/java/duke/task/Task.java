package duke.task;

import duke.util.TaskType;

/**
 * Represent a task.
 */
public abstract class Task {
    /**
     * duke.task.Task description.
     */
    private String desc;
    /**
     * Indicator for task completion.
     */
    private boolean isDone;

    /**
     * Constructor for duke.task.Task.
     * @param desc duke.task.Task description.
     */
    Task(String desc) {
        this.desc = desc.strip();
        this.isDone = false;
    }

    /**
     * Return task description.
     * @return duke.task.Task description.
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

    public abstract TaskType getType();

    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.isDone ? '\u2713' : '\u2717',
                this.desc);
    }
}
