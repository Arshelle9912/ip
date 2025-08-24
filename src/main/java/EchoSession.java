import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class EchoSession {
    private static final String CMD_LIST = "list";
    private static final String CMD_BYE = "bye";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DEADLINE = "deadline";
    private final List<Task> tasks = new ArrayList<>();
    private final String line;

    public EchoSession(String line) {
        this.line = line;
    }

    public boolean handleCommand(String command, IO io) {
        io.writeLine(line);
        try {
            command = command.trim();
            String[] parts = command.split(" ");
            command = command.trim();
            if (CMD_BYE.equals(command)) {
                bye(io);
                return false;
            }
            if (CMD_LIST.equals(command)) {
                list(io);
                return true;
            }
            if (CMD_MARK.equals(parts[0])) {
                if (parts.length < 2) throw new MissingArgumentException(parts[0]);
                int num;
                try {
                    num = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException(parts[1]);
                }
                if (num>tasks.size() || num<1) throw new InvalidIndexException(parts[1]);
                mark(Integer.parseInt(parts[1]), io);
                return true;
            }
            if (CMD_UNMARK.equals(parts[0])) {
                if (parts.length < 2) throw new MissingArgumentException(parts[0]);
                int num;
                try {
                    num = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException(parts[1]);
                }
                if (num>tasks.size() || num<1) throw new InvalidIndexException(parts[1]);
                unmark(Integer.parseInt(parts[1]), io);
                return true;
            }
            if (CMD_TODO.equals(parts[0])) {
                if (command.length() <= 4) throw new EmptyDescriptionException("ToDo");
                todo(command, io);
                return true;
            }
            if (CMD_EVENT.equals(parts[0])) {
                if (parts.length < 2) throw new EmptyDescriptionException("Event");
                event(command, io);
                return true;
            }
            if (CMD_DEADLINE.equals(parts[0])) {
                if (parts.length < 2) throw new EmptyDescriptionException("Deadline");
                deadline(command, io);
                return true;
            }
            throw new UnknownCommandException(command);
        } catch (SonOfAntonException e) {
            io.writeLine(" " + e.getMessage());
            io.writeLine(line);
            return true;
        }
    }

    private void bye(IO io) {
        io.writeLine(" Bye. Hope to see you again soon!");
        io.writeLine(line);
    }

    private void list(IO io) {
        io.writeLine(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            io.writeLine(" " + i + "." + tasks.get(i-1));
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

    private void todo(String command, IO io) {
        String description = command.substring(5);
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        io.writeLine(" Got it. I've added this task:");
        io.writeLine("   " + todo.toString());
        io.writeLine(" Now you have " + tasks.size() + " tasks in the list.");
        io.writeLine(line);
    }

    private void deadline(String command, IO io) throws SonOfAntonException {
        String description = command.substring(9);
        description = description.trim();
        String[] split = description.split("/by");
        if (split.length < 2) throw new MissingArgumentException("Deadline");
        Deadline deadline = new Deadline(split[0].trim(), split[1].trim());
        tasks.add(deadline);
        io.writeLine(" Got it. I've added this task:");
        io.writeLine("   " + deadline.toString());
        io.writeLine(" Now you have " + tasks.size() + " tasks in the list.");
        io.writeLine(line);
    }

    private void event(String command, IO io) throws SonOfAntonException {
        command = command.trim();
        String[] parts1 = command.split("/from");
        if (parts1.length < 2) throw new MissingArgumentException("Event");
        String description = parts1[0].substring(6).trim();
        String[] parts2 = parts1[1].split("/to");
        if (parts2.length < 2) throw new MissingArgumentException("Event");
        String from =  parts2[0].trim();
        String to = parts2[1].trim();
        Event event = new Event(description, from, to);
        tasks.add(event);
        io.writeLine(" Got it. I've added this task:");
        io.writeLine("   " + event.toString());
        io.writeLine(" Now you have " + tasks.size() + " tasks in the list.");
        io.writeLine(line);
    }
}