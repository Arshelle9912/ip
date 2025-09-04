package com.ip.arshelle.command;

import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.Task;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds tasks whose textual representation contains the given keyword (case-insensitive).
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a new {@code FindCommand}.
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Lists all matching tasks that contain the keyword (case-insensitive).
     * Does not modify the task list.
     *
     * @param tasks   the task list to search
     * @param ui      the user interface used to show messages
     * @param storage persistent storage (unused)
     * @return {@code true} to continue running the application
     * @throws SonOfAntonException if the keyword is empty/blank
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException {
        ui.showMessage(" Here are the matching tasks in your list:");
        String needle = keyword.toLowerCase();

        List<Task> matches = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().toLowerCase().contains(needle)) {
                matches.add(t);
            }
        }

        if (matches.isEmpty()) {
            ui.showMessage("  (no matching tasks)");
        } else {
            for (int i = 0; i < matches.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + matches.get(i));
            }
        }

        ui.showLine();
        return true;
    }
}