package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Handles the bye command which shows a farewell message,
 * saves tasks, and signals the application to exit.
 */
public class ByeCommand implements Command {
    /**
     * Executes the bye command by showing a farewell message,
     * saving the task list, and returning {@code false} to stop the application.
     *
     * @param tasks   the task list to persist
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code false} to stop running the application
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(" Bye. Hope to see you again soon!");
        ui.showLine();
        try {
            storage.saveTasks(tasks.asList());
        } catch (Exception ignored) {}
        return false;
    }

    /**
     * Always returns {@code true} to indicate that this command exits the application.
     *
     * @return {@code true}
     */
    @Override public boolean isExit() { return true; }
}