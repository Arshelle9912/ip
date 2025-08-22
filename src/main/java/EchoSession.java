import java.util.ArrayList;
import java.util.List;

public class EchoSession {
    private static final String CMD_LIST = "list";
    private static final String CMD_BYE = "bye";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private final List<Task> tasks = new ArrayList<>();
    private final String line;

    public EchoSession(String line) {
        this.line = line;
    }

    public boolean handleCommand(String command, IO io) {
        io.writeLine(line);
        String[] parts = command.split(" ");
        if (CMD_BYE.equals(command)) {
            bye(io);
            return false;
        }
        if (CMD_LIST.equals(command)) {
            list(io);
            return true;
        }
        if (CMD_MARK.equals(parts[0])) {
            mark(Integer.parseInt(parts[1]), io);
            return true;
        }
        if (CMD_UNMARK.equals(parts[0])) {
            unmark(Integer.parseInt(parts[1]), io);
            return true;
        }
        add(command, io);
        return true;
    }

    private void bye(IO io) {
        io.writeLine(" Bye. Hope to see you again soon!");
        io.writeLine(line);
    }

    private void list(IO io) {
        io.writeLine(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            io.writeLine(i + ". " + tasks.get(i-1));
        }
        io.writeLine(line);
    }

    private void add(String command, IO io) {
        tasks.add(new Task(command));
        io.writeLine("added: " + command);
        io.writeLine(line);
    }

    private void mark(int number, IO io) {
        tasks.get(number - 1).mark();
        io.writeLine("Nice! I've marked this task as done:");
        io.writeLine(tasks.get(number - 1).toString());
        io.writeLine(line);
    }

    private void unmark(int number, IO io) {
        tasks.get(number - 1).unmark();
        io.writeLine("OK, I've marked this task as not done yet:");
        io.writeLine(tasks.get(number - 1).toString());
        io.writeLine(line);
    }
}