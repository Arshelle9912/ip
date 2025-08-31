package com.ip.arshelle;

public class DeleteCommand implements Command {
    private final int indexOneBased;
    public DeleteCommand(int indexOneBased) { this.indexOneBased = indexOneBased; }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        Task removed = tasks.delete(indexOneBased);
        ui.showMessage(" Noted. I've removed this task:");
        ui.showMessage("   " + removed.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        try { storage.saveTasks(tasks.asList()); } catch (Exception ignored) {}
        return true;
    }
}
