package com.ip.arshelle.app;

import com.ip.arshelle.ui.Ui;

/**
 * Entry point of the SonOfAnton application.
 * Initializes the UI, displays the welcome logo, and starts the echo loop.
 */
public class SonOfAnton {
    /**
     * Launches the application.
     *
     * @param args command line arguments (not used)
     */
    public void getResponse(String args) {

        Ui ui = new Ui();
        EchoSession echoSession = new EchoSession(ui);
        echoSession.handleCommand(args);
    }
}