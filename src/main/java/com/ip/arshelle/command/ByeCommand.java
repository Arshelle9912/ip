package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class ByeCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(" Bye. Hope to see you again soon!");
        ui.showLine();
        try {
            storage.saveTasks(tasks.asList());
        } catch (Exception ignored) {}
        return false;
    }
    @Override public boolean isExit() { return true; }
}