package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.Task;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Deletes a task from the task list using its 1-based index.
 */
public class DeleteCommand implements Command {
    private final int indexOneBased;

    /**
     * Creates a new {@code DeleteCommand}.
     *
     * @param indexOneBased the 1-based index of the task to delete
     */
    public DeleteCommand(int indexOneBased) {
        this.indexOneBased = indexOneBased;
    }

    /**
     * Executes the delete command by removing the specified task,
     * showing feedback to the user, saving the updated list, and continuing execution.
     *
     * @param tasks   the task list to update
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running the application
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        assert indexOneBased > 0 : "index must be positive";
        assert indexOneBased <= tasks.size()
                : "index must be within current list size";
        Task removed = tasks.delete(indexOneBased);
        ui.showMessage(" Noted. I've removed this task:");
        ui.showMessage("   " + removed.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        CommandUtils.saveQuietly(storage, tasks);
        return true;
    }
}
