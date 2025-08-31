package com.ip.arshelle;

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
