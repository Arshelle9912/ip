package com.ip.arshelle;

import com.ip.arshelle.task.Task;
import com.ip.arshelle.task.TaskList;
import com.ip.arshelle.task.ToDo;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskListTest {

    @Test
    public void addAndGet() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0).toString().contains("read book"));
    }

    @Test
    public void markAndUnmark() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));

        tasks.mark(1); // one-based index
        assertTrue("Task should be marked done after mark(1)", tasks.get(0).isDone());

        tasks.unmark(1);
        assertFalse("Task should not be done after unmark(1)", tasks.get(0).isDone());
    }

    @Test
    public void delete() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("task A"));
        tasks.add(new ToDo("task B"));

        Task removed = tasks.delete(1);
        assertTrue(removed.toString().contains("task A"));
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0).toString().contains("task B"));
    }
}
