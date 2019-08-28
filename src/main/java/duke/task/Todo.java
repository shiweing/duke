package duke.task;

import duke.util.TaskType;

/**
 * Represent a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for duke.task.Todo.
     * @param desc duke.task.Task description.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns duke.util.TaskType.TODO.
     * @return Type TODO.
     */
    public TaskType getType() {
        return TaskType.TODO;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
