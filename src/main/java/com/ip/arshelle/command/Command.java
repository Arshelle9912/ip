package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;
import com.ip.arshelle.exceptions.SonOfAntonException;

/**
 * Represents a user command that can be executed on the task list, UI, and storage.
 */
public interface Command {
    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param tasks   the task list to operate on
     * @param ui      the user interface used to interact with the user
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running, {@code false} to exit
     * @throws SonOfAntonException if the command cannot be executed successfully
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException;

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return {@code true} if the application should exit, {@code false} otherwise
     */
    default boolean isExit() { return false; }
}