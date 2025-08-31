package com.ip.arshelle.app;

import com.ip.arshelle.ui.Ui;

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