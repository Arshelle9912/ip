package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

public class HelpCommand implements Command {
    private static final String HELP_TEXT = String.join("\n",
            "Commands:",
            "  list",
            "  todo <desc>",
            "  deadline <desc> /by <yyyy-MM-dd HHmm>",
            "  event <desc> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>",
            "  mark <index>",
            "  unmark <index>",
            "  delete <index>",
            "  find <keyword>",
            "  bye",
            "  help"
    );

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(HELP_TEXT);
        ui.showLine();
        return true;
    }
}
