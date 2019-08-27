package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return this.list;
    }

    public int size() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int taskId) throws IndexOutOfBoundsException {
        return list.remove(taskId);
    }

    public Task done(int taskId) throws IndexOutOfBoundsException {
        Task task = list.get(taskId);
        task.done();
        return task;
    }
}
