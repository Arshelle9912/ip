package com.ip.arshelle.exceptions;

public class UnknownCommandException extends SonOfAntonException {
    public UnknownCommandException(String cmd) {
        super("I don't recognise \"" + cmd + "\". Type 'help' to see the list of available commands.");
    }
}
