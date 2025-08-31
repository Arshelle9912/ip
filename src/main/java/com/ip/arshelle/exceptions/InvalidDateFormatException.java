package com.ip.arshelle.exceptions;

public class InvalidDateFormatException extends SonOfAntonException {
    public InvalidDateFormatException(String cmd) {
        super("Invalid Date Format for " + cmd + ". Expected yyyy-MM-dd");
    }
}