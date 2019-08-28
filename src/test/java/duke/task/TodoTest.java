package duke.task;

import duke.util.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals(new Todo("read book").toString(), "[T][\u2717] read book");
    }

    @Test
    public void doneTest() {
        Todo todo = new Todo("read book");
        assertEquals(todo.isDone(), false);

        todo.done();
        assertEquals(todo.isDone(), true);
    }

    @Test
    public void getTypeTest() {
        assertEquals(new Todo("read book").getType(), TaskType.TODO);
    }
}
