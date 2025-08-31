package com.ip.arshelle;

import com.ip.arshelle.exceptions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
    private static final String CMD_DELETE = "delete";
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    public EchoSession(Ui ui) {
        this.ui = ui;
        this.storage = new Storage("./data/duke.txt");
        TaskList loaded;
        try {
            loaded = new TaskList(storage.getTasks());
        } catch (IOException e) {
            ui.showMessage("Warning: could not load tasks: " + e.getMessage());
            loaded = new TaskList();
        }
        this.tasks = loaded;
    }

    public boolean handleCommand(String command) {
        ui.showLine();
        try {
            command = command.trim();
            String[] parts = command.split(" ");
            command = command.trim();
            if (CMD_BYE.equals(command)) {
                bye();
                return false;
            }
            if (CMD_LIST.equals(command)) {
                list();
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
                mark(num);
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
                unmark(num);
                return true;
            }
            if (CMD_TODO.equals(parts[0])) {
                if (command.length() <= 4) throw new EmptyDescriptionException("ToDo");
                todo(command);
                return true;
            }
            if (CMD_EVENT.equals(parts[0])) {
                if (parts.length < 2) throw new EmptyDescriptionException("Event");
                event(command);
                return true;
            }
            if (CMD_DEADLINE.equals(parts[0])) {
                if (parts.length < 2) throw new EmptyDescriptionException("Deadline");
                deadline(command);
                return true;
            }
            if (CMD_DELETE.equals(parts[0])) {
                if (parts.length < 2) throw new MissingArgumentException("Delete");
                int num;
                try {
                    num = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidIndexException(parts[1]);
                }
                if (num>tasks.size() || num<1) throw new InvalidIndexException(parts[1]);
                delete(num);
                return true;
            }
            throw new UnknownCommandException(command);
        } catch (SonOfAntonException e) {
            ui.showMessage(" " + e.getMessage());
            ui.showLine();
            return true;
        }
    }

    private void bye() {
        ui.showMessage(" Bye. Hope to see you again soon!");
        ui.showLine();
        save();
    }

    private void list() {
        ui.showMessage(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            ui.showMessage(" " + i + "." + tasks.get(i-1));
        }
        ui.showLine();
    }

    private void mark(int number) {
        tasks.mark(number);
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(tasks.get(number - 1).toString());
        ui.showLine();
        save();
    }

    private void unmark(int number) {
        tasks.unmark(number);
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(tasks.get(number - 1).toString());
        ui.showLine();
        save();
    }

    private void todo(String command) throws SonOfAntonException {
        String description = command.substring(5);
        if (description.isEmpty()) throw new EmptyDescriptionException("ToDo");
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + todo.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        save();
    }

    private void deadline(String command) throws SonOfAntonException {
        String description = command.substring(9);
        description = description.trim();
        String[] split = description.split("/by");
        if (split.length < 2) throw new MissingArgumentException("Deadline");
        Deadline deadline = Deadline.of(split[0].trim(), split[1].trim());
        tasks.add(deadline);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + deadline.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        save();
    }

    private void event(String command) throws SonOfAntonException {
        command = command.trim();
        String[] parts1 = command.split("/from");
        if (parts1.length < 2) throw new MissingArgumentException("Event");
        String description = parts1[0].substring(6).trim();
        String[] parts2 = parts1[1].split("/to");
        if (parts2.length < 2) throw new MissingArgumentException("Event");
        String from =  parts2[0].trim();
        String to = parts2[1].trim();
        Event event = Event.of(description, from, to);
        tasks.add(event);
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + event.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        save();
    }

    private void delete(int num) {
        Task removed = tasks.delete(num);
        ui.showMessage(" Noted. I've removed this task:");
        ui.showMessage("   " + removed.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        save();
    }

    private void save() {
        try {
            storage.saveTasks(tasks.asList());
        } catch (IOException e) {
            System.out.println("Warning: could not save tasks: " + e.getMessage());
        }
    }
}