package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class MarkCommand implements Command {
    private final int indexOneBased;
    public MarkCommand(int indexOneBased) { this.indexOneBased = indexOneBased; }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(indexOneBased);
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(tasks.get(indexOneBased - 1).toString());
        ui.showLine();
        try { storage.saveTasks(tasks.asList()); } catch (Exception ignored) {}
        return true;
    }
}