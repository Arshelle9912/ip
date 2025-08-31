package com.ip.arshelle;

import com.ip.arshelle.exceptions.SonOfAntonException;

public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws SonOfAntonException;
    default boolean isExit() { return false; }
}