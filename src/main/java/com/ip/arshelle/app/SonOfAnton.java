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
    public static void main(String[] args) {
        String logo = "_    _   _ _______  ____  _   _ \n"
                + "   / \\  | \\ | |__   __|/ __ \\| \\ | |\n"
                + "  / _ \\ |  \\| |  | |  | |  | |  \\| |\n"
                + " / ___ \\| |\\  |  | |  | |__| | |\\  |\n"
                + "/_/   \\_\\_| \\_|  |_|   \\____/|_| \\_|\n";

        Ui ui = new Ui();
        ui.showWelcome(logo);
        Echo echo = new Echo();
        echo.start(ui);
    }
}