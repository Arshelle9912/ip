package exceptions;

public class InvalidIndexException extends SonOfAntonException {
    public InvalidIndexException(String cmd) {
        super("Invalid index entered in \"" + cmd + "\".");
    }
}