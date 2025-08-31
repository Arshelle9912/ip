package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.task.ToDo;
import com.ip.arshelle.ui.Ui;

public class AddTodoCommand implements Command {
    private final String desc;
    public AddTodoCommand(String desc) { this.desc = desc; }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = new ToDo(desc);
        tasks.add(todo);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + todo.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        try { storage.saveTasks(tasks.asList()); } catch (Exception ignored) {}
        return true;
    }
}