import java.util.ArrayList;
import java.util.List;

public class EchoSession {
    private static final String CMD_LIST = "list";
    private static final String CMD_BYE = "bye";
    private final List<String> items = new ArrayList<>();
    private final String line;

    public EchoSession(String line) {
        this.line = line;
    }

    public boolean handleCommand(String command, IO io) {
        io.writeLine(line);
        if (CMD_BYE.equals(command)) {
            io.writeLine(" Bye. Hope to see you again soon!");
            io.writeLine(line);
            return false;
        }
        if (CMD_LIST.equals(command)) {
            for (int i = 1; i <= items.size(); i++) {
                io.writeLine(i + ". " + items.get(i - 1));
            }
            io.writeLine(line);
            return true;
        }
        items.add(command);
        io.writeLine("added: " + command);
        io.writeLine(line);
        return true;
    }
}
