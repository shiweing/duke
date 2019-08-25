/**
 * Represent a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param desc Task description.
     */
    Todo(String desc) {
        super(desc);
    }

    /**
     * Another constructor for Todo.
     * @param desc Task description.
     * @param isDone Task completion.
     */
    Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Returns TaskType.TODO.
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
