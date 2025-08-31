package com.ip.arshelle.command;

import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.Deadline;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class AddDeadlineCommand implements Command {
    private final String desc;
    private final String byRaw;

    public AddDeadlineCommand(String desc, String byRaw) {
        this.desc = desc;
        this.byRaw = byRaw;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException {
        Deadline deadline = Deadline.of(desc, byRaw);
        tasks.add(deadline);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + deadline.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        try { storage.saveTasks(tasks.asList()); } catch (Exception ignored) {}
        return true;
    }
}