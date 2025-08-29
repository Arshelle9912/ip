package com.ip.arshelle.exceptions;

public class UnknownCommandException extends SonOfAntonException {
    public UnknownCommandException(String cmd) {
        super("I don't recognise \"" + cmd + "\". Try: list, todo, deadline, event, mark, unmark, bye.");;
    }
}
