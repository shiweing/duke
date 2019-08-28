package duke.util;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void isEmptyTest() {
        assertEquals(new TaskList().isEmpty(), true);

        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));
        assertEquals(taskList.isEmpty(), false);
    }

    @Test
    public void doneTest() {
        Todo todo = new Todo("read book");
        TaskList taskList = new TaskList();
        taskList.add(todo);

        assertEquals(taskList.done(0), todo);

        try {
            assertEquals(taskList.done(1), todo);
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
