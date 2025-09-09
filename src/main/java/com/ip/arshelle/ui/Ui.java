package com.ip.arshelle.ui;

import com.ip.arshelle.task.Task;
import com.ip.arshelle.task.TaskList;

import java.util.Scanner;
import java.util.function.Consumer;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    private final Scanner sc = new Scanner(System.in);
    private final Consumer<String> sink;

    public Ui() {
        this(System.out::println);
    }

    public Ui(Consumer<String> sink) {
        this.sink = (sink == null) ? System.out::println : sink;
    }

    private void out(String s) {
        sink.accept(s);
    }

    public void showWelcome(String asciiLogo) {
        out("Hello from Son of");
        out(asciiLogo);
        showLine();
        out(" What can I do for you?");
        showLine();
    }

    public void showLine() {
        out(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String msg) {
        out(" " + msg);
        showLine();
    }

    public void showMessage(String msg) {
        out(" " + msg);
    }

    public void showList(TaskList tasks) {
        out(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            out(" " + i + "." + tasks.get(i - 1));
        }
        showLine();
    }

    public void showAdded(Task t, int newSize) {
        out(" Got it. I've added this task:");
        out("   " + t);
        out(" Now you have " + newSize + " tasks in the list.");
        showLine();
    }

    public void showRemoved(Task t, int newSize) {
        out(" Noted. I've removed this task:");
        out("   " + t);
        out(" Now you have " + newSize + " tasks in the list.");
        showLine();
    }

    public void showBye() {
        out(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLoadingError() {
        out(" Warning: could not load tasks. Starting with an empty list.");
        showLine();
    }
}