package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Marks a task as not done in the task list using its 1-based index.
 */
public class UnmarkCommand implements Command {
    private final int indexOneBased;

    /**
     * Creates a new {@code UnmarkCommand}.
     *
     * @param indexOneBased the 1-based index of the task to unmark
     */
    public UnmarkCommand(int indexOneBased) { this.indexOneBased = indexOneBased; }

    /**
     * Executes the unmark command by marking the specified task as not done,
     * showing feedback to the user, saving the updated list, and continuing execution.
     *
     * @param tasks   the task list to update
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running the application
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(indexOneBased);
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(tasks.get(indexOneBased - 1).toString());
        ui.showLine();
        try { storage.saveTasks(tasks.asList()); } catch (Exception ignored) {}
        return true;
    }
}
