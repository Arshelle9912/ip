package com.ip.arshelle;

import java.util.Scanner;

public class Echo {
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