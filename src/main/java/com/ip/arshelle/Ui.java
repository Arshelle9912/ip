package com.ip.arshelle;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome(String asciiLogo) {
        System.out.println("Hello from Son of\n" + asciiLogo);
        showLine();
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String msg) {
        System.out.println(" " + msg);
        showLine();
    }

    public void showMessage(String msg) {
        System.out.println(" " + msg);
    }

    public void showList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(" " + i + "." + tasks.get(i - 1));
        }
        showLine();
    }

    public void showAdded(Task t, int newSize) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + newSize + " tasks in the list.");
        showLine();
    }

    public void showRemoved(Task t, int newSize) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + newSize + " tasks in the list.");
        showLine();
    }

    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLoadingError() {
        System.out.println(" Warning: could not load tasks. Starting with an empty list.");
        showLine();
    }
}
