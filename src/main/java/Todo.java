public class Todo extends Task {
    Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
