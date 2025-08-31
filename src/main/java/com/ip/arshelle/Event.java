package com.ip.arshelle;

import com.ip.arshelle.exceptions.InvalidDateFormatException;
import com.ip.arshelle.exceptions.SonOfAntonException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public static Event of (String description, String from, String to) throws SonOfAntonException {
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Either " + from + " or " + to);
        }
        return new Event(description, fromDate, toDate);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}