package com.ip.arshelle.exceptions;

public class EmptyDescriptionException extends SonOfAntonException {
    public EmptyDescriptionException(String cmd) {
      super("Description of a " + cmd + " can't be empty.");
    }
}