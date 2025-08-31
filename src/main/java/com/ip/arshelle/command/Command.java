package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;
import com.ip.arshelle.exceptions.SonOfAntonException;

public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException;
    default boolean isExit() { return false; }
}