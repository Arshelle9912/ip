package com.ip.arshelle.app;

import com.ip.arshelle.ui.Ui;

import java.util.Scanner;

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

    public static void main(String[] args) {
        Ui ui = new Ui();
        EchoSession session = new EchoSession(ui);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (!session.handleCommand(line)) break;
        }
    }
}