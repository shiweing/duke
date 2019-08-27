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
