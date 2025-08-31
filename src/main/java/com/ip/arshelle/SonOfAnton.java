package com.ip.arshelle;

import java.util.Scanner;

public class SonOfAnton {
    private static final String LINE = "____________________________________________________________";
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