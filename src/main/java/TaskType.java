public enum TaskType {
    TODO('T'),
    DEADLINE('D'),
    EVENT('E');

    private char tag;

    TaskType(char tag) {
        this.tag = tag;
    }

    String taskToString(Task task) throws DukeException {
        switch (this) {
        case TODO:
            Todo todo = (Todo) task;
            return String.format("%c | %b | %s\n", this.tag, todo.isDone(), todo.getDesc());
        case DEADLINE:
            Deadline deadline = (Deadline) task;
            return String.format("%c | %b | %s | %s\n", this.tag, deadline.isDone(), deadline.getDesc(), deadline.getBy());
        case EVENT:
            Event event = (Event) task;
            return String.format("%c | %b | %s | %s\n", this.tag, event.isDone(), event.getDesc(), event.getAt());
        default:
            throw new DukeException("Unknown type: " + this);
        }
    }
}
