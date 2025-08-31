package com.ip.arshelle;

import com.ip.arshelle.exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static Deadline of(String description, String by) throws InvalidDateFormatException {
        LocalDate date;
        try {
            date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(by);
        }
        return new Deadline(description, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}