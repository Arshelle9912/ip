package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Lists all tasks in the task list with their 1-based indices.
 */
public class ListCommand implements Command {
    /**
     * Executes the list command by printing all tasks in the task list,
     * showing them with indices via the UI, and continuing execution.
     *
     * @param tasks   the task list to display
     * @param ui      the user interface used to show messages
     * @param storage persistent storage (not used here)
     * @return {@code true} to continue running the application
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(" Here are the tasks in your list:");
        for (String line : tasks.toIndexedLines()) {
            ui.showMessage(line);
        }
        ui.showLine();
        return true;
    }
}
