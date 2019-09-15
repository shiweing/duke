package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.util.DateFormatter;
import duke.util.DukeException;
import duke.util.Error;
import duke.util.TaskList;
import duke.util.TaskType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;

public class UpdateCommand extends Command {
    private int taskId;
    private String attributes;

    /**
     * Constructor for UpdateCommand.
     * @param input command attributes, include task number and task atributes to update.
     * @throws DukeException if task number attributes are invalid.
     */
    public UpdateCommand(String input) throws DukeException {
        try {
            String[] inputArr = input.split(" ", 2);
            // inputArr[0]: taskId | inputArr[1]: attributes to update
            taskId = Integer.parseInt(inputArr[0].strip()) - 1;

            // throw exception if attributes not provided
            if (inputArr.length < 2) {
                throw new DukeException(Error.UPDATE_INVALID_ATTRIBUTES.getErrorString());
            }

            attributes = inputArr[1].strip();
            // throw exception if attributes not in correct format
            if (!attributes.contains("--")) {
                throw new DukeException(Error.UPDATE_INVALID_ATTRIBUTES.getErrorString());
            }
        } catch (NumberFormatException e) {
            throw new DukeException(Error.UPDATE.getErrorString());
        }
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            Task task = tasks.get(taskId);
            // Remove empty strings from split
            String[] updateArr = Arrays.asList(attributes.split("--"))
                    .stream()
                    .filter(str -> !str.isEmpty())
                    .toArray(String[]::new);

            // List of updates to execute after input processing
            ArrayList<Consumer<Task>> updates = new ArrayList<>();
            for (String updateStr : updateArr) {
                updateStr = updateStr.strip();

                updates.add(getUpdateMethod(task, updateStr));
            }

            // Execute updates
            for (Consumer<Task> update : updates) {
                update.accept(task);
            }

            return String.format("Got it. I've updated this task:%n"
                            + "\t%s",
                    task);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Error.UPDATE.getErrorString());
        }
    }

    private Consumer<Task> getUpdateMethod(Task task, String updateStr) throws DukeException {
        // Split into update attribute and update value
        String[] updateArr = updateStr.split(" ", 2);

        if (updateArr.length < 2) {
            throw new DukeException(Error.UPDATE_INVALID_ATTRIBUTES.getErrorString());
        }

        String updateAttr = updateArr[0].strip();
        String updateVal = updateArr[1].strip();

        switch (updateAttr) {
        case "desc":
            return getUpdateDescMethod(updateVal);
        case "by":
            return getUpdateDeadlineMethod(task, updateVal);
        case "at":
            return getUpdateEventTimeMethod(task, updateVal);
        default:
            throw new DukeException(Error.UPDATE_INVALID_ATTRIBUTES.getErrorString());
        }
    }

    private Consumer<Task> getUpdateDescMethod(String desc) {
        return task -> updateDesc(task, desc);
    }

    private Consumer<Task> getUpdateDeadlineMethod(Task task, String deadlineStr) throws DukeException {
        if (task.getType() != TaskType.DEADLINE) {
            throw new DukeException(Error.UPDATE.getErrorString());
        }

        Date deadline = DateFormatter.parse(deadlineStr, Error.DEADLINE_FORMAT.getErrorString());
        return taskToUpdate -> updateDeadline(taskToUpdate, deadline);
    }

    private Consumer<Task> getUpdateEventTimeMethod(Task task, String eventTimeStr) throws DukeException {
        if (task.getType() != TaskType.EVENT) {
            throw new DukeException(Error.UPDATE.getErrorString());
        }

        Date eventTime = DateFormatter.parse(eventTimeStr, Error.EVENT_TIME_FORMAT.getErrorString());
        return taskToUpdate -> updateEventTime(taskToUpdate, eventTime);
    }

    private void updateDesc(Task task, String desc) {
        task.setDesc(desc);
    }

    private void updateDeadline(Task task, Date deadline) {
        ((Deadline)task).setBy(deadline);
    }

    private void updateEventTime(Task task, Date eventTime) {
        ((Event)task).setAt(eventTime);
    }
}


