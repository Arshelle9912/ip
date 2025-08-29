package com.ip.arshelle;

import java.util.Scanner;

public class ScannerIO implements IO {
    private final Scanner scanner;
    public ScannerIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String s) {
        System.out.println(s);
    }
}
