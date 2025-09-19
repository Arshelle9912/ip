package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Marks a task as done in the task list using its 1-based index.
 */
public class MarkCommand implements Command {
    private final int indexOneBased;

    /**
     * Creates a new {@code MarkCommand}.
     *
     * @param indexOneBased the 1-based index of the task to mark as done
     */
    public MarkCommand(int indexOneBased) {
        this.indexOneBased = indexOneBased;
    }

    /**
     * Executes the mark command by marking the specified task as done,
     * showing feedback to the user, saving the updated list, and continuing execution.
     *
     * @param tasks   the task list to update
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running the application
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        assert indexOneBased > 0 : "index must be 1-based positive";
        assert indexOneBased <= tasks.size()
                : "index must not exceed current list size";
        tasks.mark(indexOneBased);
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(tasks.get(indexOneBased - 1).toString());
        ui.showLine();
        try {
            storage.saveTasks(tasks.asList());
        } catch (Exception ignored) {
            // ignore as saving is non-critical
        }
        return true;
    }
}