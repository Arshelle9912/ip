package com.ip.arshelle.app;

import com.ip.arshelle.ui.Ui;
/**
 * Runs an interactive echo loop that reads user input and delegates handling to an EchoSession.
 */
public class Echo {
    /**
     * Starts the echo session, reading commands from the given UI until the session ends.
     *
     * @param ui the user interface used to read input and show output
     */
    public void start(Ui ui) {
        EchoSession session = new EchoSession(ui);
        while (true) {
            String input = ui.readCommand();
            if (!session.handleCommand(input)) {
                break;
            }
        }
    }
}