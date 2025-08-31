package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class ListCommand implements Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            ui.showMessage(" " + i + "." + tasks.get(i - 1));
        }
        ui.showLine();
        return true;
    }
}
