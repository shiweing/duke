package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Handles list of tasks.
 */
public class TaskList {
    /**
     * List of tasks.
     */
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Returns if list is empty.
     * @return true if list is empty, false is list is not empty.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns list of tasks.
     * @return List of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.list;
    }

    /**
     * Returns size of task list.
     * @return size of task list
     */
    public int size() {
        return list.size();
    }

    public Task get(int taskId) throws IndexOutOfBoundsException {
        return list.get(taskId);
    }

    /**
     * Adds task to list.
     * @param task Task to add.
     * @return true if task added, false if task not added.
     */
    public boolean add(Task task) {
        return list.add(task);
    }

    /**
     * Removes task from list.
     * @param taskId Task id of task to delete from list.
     * @return Deleted task.
     * @throws IndexOutOfBoundsException if task id provided is not within list index.
     */
    public Task delete(int taskId) throws IndexOutOfBoundsException {
        return list.remove(taskId - 1);
    }

    /**
     * Marks task as done.
     * @param taskId Task id of task to mark as done.
     * @return Task marked as done.
     * @throws IndexOutOfBoundsException if task id provided is not within list index.
     */
    public Task done(int taskId) throws IndexOutOfBoundsException {
        Task task = list.get(taskId - 1);
        task.done();
        assert task.isDone(): "Task not marked done successfully";
        return task;
    }
}
