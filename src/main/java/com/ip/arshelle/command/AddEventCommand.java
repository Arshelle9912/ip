package com.ip.arshelle.command;

import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.Event;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class AddEventCommand implements Command {
    private final String desc;
    private final String fromRaw;
    private final String toRaw;

    public AddEventCommand(String desc, String fromRaw, String toRaw) {
        this.desc = desc;
        this.fromRaw = fromRaw;
        this.toRaw = toRaw;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException {
        Event event = Event.of(desc, fromRaw, toRaw);
        tasks.add(event);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + event.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        try { storage.saveTasks(tasks.asList()); } catch (Exception ignored) {}
        return true;
    }
}