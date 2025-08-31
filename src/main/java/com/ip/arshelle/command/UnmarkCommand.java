package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class UnmarkCommand implements Command {
    private final int indexOneBased;
    public UnmarkCommand(int indexOneBased) { this.indexOneBased = indexOneBased; }

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
