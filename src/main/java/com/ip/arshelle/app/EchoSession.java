package com.ip.arshelle.app;

import com.ip.arshelle.command.Command;
import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.parser.Parser;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

import java.io.IOException;

public class EchoSession {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    public EchoSession(Ui ui) {
        this.ui = ui;
        this.storage = new Storage("./data/duke.txt");
        TaskList loaded;
        try {
            loaded = new TaskList(storage.getTasks());
        } catch (IOException e) {
            ui.showMessage("Warning: could not load tasks: " + e.getMessage());
            loaded = new TaskList();
        }
        this.tasks = loaded;
    }

    public boolean handleCommand(String input) {
        ui.showLine();
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (SonOfAntonException e) {
            ui.showMessage(" " + e.getMessage());
            ui.showLine();
            return true;
        }
    }
}