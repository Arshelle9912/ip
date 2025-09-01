package com.ip.arshelle;

import com.ip.arshelle.exceptions.InvalidDateFormatException;
import com.ip.arshelle.exceptions.SonOfAntonException;
import com.ip.arshelle.task.Deadline;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class DeadlineTest {
    @Test
    public void validDeadline() throws SonOfAntonException {
        Deadline d = Deadline.of("return book", "2019-10-15");

        String printed = d.toString();
        assertTrue(printed.contains("return book"));
        assertTrue("Expected date in MMM DD, YYYY format, but got: " + printed,
                printed.contains("Oct 15, 2019"));
    }

    @Test
    public void invalidDeadline() {
        assertThrows(InvalidDateFormatException.class,
                () -> Deadline.of("return book", "2019/10/15"));
        assertThrows(InvalidDateFormatException.class,
                () -> Deadline.of("return book", "not-a-date"));
    }
}