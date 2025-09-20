package com.ip.arshelle.command;

import com.ip.arshelle.storage.Storage;
import com.ip.arshelle.task.TaskList;

public final class CommandUtils {
    private CommandUtils() {}

    public static void saveQuietly(Storage storage, TaskList tasks) {
        try { storage.saveTasks(tasks.asList()); }
        catch (Exception ignored) {
            /* non-critical save; see design */
        }
    }
}