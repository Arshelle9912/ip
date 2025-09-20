package com.ip.arshelle.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() { this.tasks = new ArrayList<>(); }

    public TaskList(List<Task> initial) { this.tasks = new ArrayList<>(initial); }

    public int size() { return tasks.size(); }

    public Task get(int indexZeroBased) { return tasks.get(indexZeroBased); }

    public Task add(Task t) { tasks.add(t); return t; }

    public Task delete(int oneBasedIndex) { return tasks.remove(oneBasedIndex - 1); }

    public void mark(int oneBasedIndex) { tasks.get(oneBasedIndex - 1).mark(); }

    public void unmark(int oneBasedIndex) { tasks.get(oneBasedIndex - 1).unmark(); }

    public List<Task> asList() { return tasks; }

    public List<String> toIndexedLines() {
        List<String> out = new ArrayList<>();
        for (int i = 1; i <= size(); i++) {
            out.add(" " + i + "." + get(i - 1));
        }
        return out;
    }
}