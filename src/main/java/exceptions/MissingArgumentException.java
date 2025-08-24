package exceptions;

public class MissingArgumentException extends SonOfAntonException {
    public MissingArgumentException(String cmd) {
        super("Missing Argument in \"" + cmd + "\".");
    }
}