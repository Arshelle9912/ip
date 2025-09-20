package com.ip.arshelle.app;

import com.ip.arshelle.command.Command;
import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.parser.Parser;
import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.ui.Ui;

import java.io.IOException;
/**
 * Manages a user session for the echo application,
 * including loading tasks, handling commands, and coordinating storage and UI.
 */
public class EchoSession {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    private static final String DATA_FILE_PATH = "./data/duke.txt";

    /**
     * Creates a new {@code EchoSession} with the given UI and loads tasks from storage.
     *
     * @param ui the user interface used to show messages and read input
     */
    public EchoSession(Ui ui) {
        this.ui = ui;
        this.storage = new Storage(DATA_FILE_PATH);
        TaskList loaded;
        try {
            loaded = new TaskList(storage.getTasks());
        } catch (IOException e) {
            ui.showMessage("Warning: could not load tasks: " + e.getMessage());
            loaded = new TaskList();
        }
        this.tasks = loaded;
    }

    /**
     * Parses and executes the given user command.
     * Shows output via the UI and returns whether the session should continue running.
     *
     * @param input the raw user input string
     * @return {@code true} if the session should continue, {@code false} to exit
     */
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