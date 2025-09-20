package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.task.ToDo;
import com.ip.arshelle.ui.Ui;

/**
 * Adds a todo task with a description to the task list.
 */
public class AddTodoCommand implements Command {
    private final String desc;

    /**
     * Creates a new {@code AddTodoCommand}.
     *
     * @param desc the description of the todo task
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the command by creating and adding a todo task,
     * showing feedback to the user, saving the updated list, and continuing execution.
     *
     * @param tasks   the task list to update
     * @param ui      the user interface used to show messages
     * @param storage persistent storage used to save tasks
     * @return {@code true} to continue running the application
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = new ToDo(desc);
        tasks.add(todo);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + todo.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        CommandUtils.saveQuietly(storage, tasks);
        return true;
    }
}