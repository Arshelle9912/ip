package com.ip.arshelle.command;

import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.Deadline;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Adds a deadline task with a description and due date/time to the task list.
 */
public class AddDeadlineCommand implements Command {
    private final String desc;
    private final String byRaw;

    /**
     * Creates a new {@code AddDeadlineCommand}.
     *
     * @param desc  the description of the deadline task
     * @param byRaw the raw date/time string representing the deadline
     */
    public AddDeadlineCommand(String desc, String byRaw) {
        this.desc = desc;
        this.byRaw = byRaw;
    }

    /**
     * Executes the command by creating and adding a deadline task,
     * showing feedback to the user, saving the updated list, and continuing execution.
     *
     * @param tasks   the task list to update
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running the application
     * @throws SonOfAntonException if the deadline date/time cannot be parsed
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException {
        Deadline deadline = Deadline.of(desc, byRaw);
        assert deadline != null : "Deadline.of must return a task";
        tasks.add(deadline);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + deadline.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        CommandUtils.saveQuietly(storage, tasks);
        return true;
    }
}