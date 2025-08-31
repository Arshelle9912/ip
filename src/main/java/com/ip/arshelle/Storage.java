package com.ip.arshelle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        sc.close();
        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task t = null;
        try {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            switch (type) {
                case "T":
                    t = new ToDo(description);
                    break;
                case "D":
                    if (parts.length < 4) {
                        return null;
                    }
                    String by = parts[3];
                    t = Deadline.of(description, by);
                    break;
                case "E":
                    if (parts.length < 5) {
                        return null;
                    }
                    String from = parts[3];
                    String to = parts[4];
                    t = Event.of(description, from, to);
                    break;
            }
            if (t != null && isDone) {
                t.mark();
            }
            return t;
        } catch (Exception e) {
            return null;
        }
    }
}
