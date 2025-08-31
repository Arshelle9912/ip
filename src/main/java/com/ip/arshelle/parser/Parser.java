package com.ip.arshelle.parser;

import com.ip.arshelle.command.*;
import com.ip.arshelle.exceptions.*;

public class Parser {
    public static Command parse(String input) throws SonOfAntonException {
        String s = input.trim();

        if (s.equals("bye"))  return new ByeCommand();
        if (s.equals("list")) return new ListCommand();

        String[] parts = s.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {
            case "mark":
                return new MarkCommand(parseIndex(parts));
            case "unmark":
                return new UnmarkCommand(parseIndex(parts));
            case "delete":
                return new DeleteCommand(parseIndex(parts));

            case "todo":
                if (parts.length < 2 || parts[1].isBlank()) {
                    throw new EmptyDescriptionException("ToDo");
                }
                return new AddTodoCommand(parts[1].trim());

            case "deadline":
                if (parts.length < 2 || parts[1].isBlank()) {
                    throw new EmptyDescriptionException("Deadline");
                }
                String[] d = parts[1].split("/by", 2);
                if (d.length < 2) throw new MissingArgumentException("Deadline");
                return new AddDeadlineCommand(d[0].trim(), d[1].trim());

            case "event":
                if (parts.length < 2 || parts[1].isBlank()) {
                    throw new EmptyDescriptionException("Event");
                }
                String[] p1 = parts[1].split("/from", 2);
                if (p1.length < 2) throw new MissingArgumentException("Event");
                String desc = p1[0].trim();
                String[] p2 = p1[1].split("/to", 2);
                if (p2.length < 2) throw new MissingArgumentException("Event");
                return new AddEventCommand(desc, p2[0].trim(), p2[1].trim());

            default:
                throw new UnknownCommandException(s);
        }
    }

    private static int parseIndex(String[] parts) throws SonOfAntonException {
        if (parts.length < 2) throw new MissingArgumentException(parts[0]);
        String arg = parts[1].trim();
        try {
            int idx = Integer.parseInt(arg);
            if (idx < 1) throw new InvalidIndexException(arg);
            return idx;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(arg);
        }
    }
}