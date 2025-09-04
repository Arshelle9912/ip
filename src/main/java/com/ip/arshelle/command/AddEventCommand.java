package com.ip.arshelle.command;

import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.Event;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

/**
 * Adds an event task with a description, start, and end date/time to the task list.
 */
public class AddEventCommand implements Command {
    private final String desc;
    private final String fromRaw;
    private final String toRaw;

    /**
     * Creates a new {@code AddEventCommand}.
     *
     * @param desc    the description of the event
     * @param fromRaw the raw start date/time string
     * @param toRaw   the raw end date/time string
     */
    public AddEventCommand(String desc, String fromRaw, String toRaw) {
        this.desc = desc;
        this.fromRaw = fromRaw;
        this.toRaw = toRaw;
    }

    /**
     * Executes the command by creating and adding an event task,
     * showing feedback to the user, saving the updated list, and continuing execution.
     *
     * @param tasks   the task list to update
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running the application
     * @throws SonOfAntonException if the event date/time range is invalid
     */
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