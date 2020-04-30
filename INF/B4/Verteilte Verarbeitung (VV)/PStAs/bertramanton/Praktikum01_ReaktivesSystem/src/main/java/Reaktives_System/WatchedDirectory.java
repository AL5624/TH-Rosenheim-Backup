package Reaktives_System;

import java.util.HashMap;
import java.util.Map;

public class WatchedDirectory
{
    /**Map<name, watchedFile> for all watched Files*/
    private Map<String, WatchedFile> allWatchedFilesWatchedDirectory = new HashMap<>();
    /**directory which is observed*/
    private DirectoryWatcher         directoryWatcher;

    public WatchedDirectory(DirectoryWatcher directoryWatcher)
    {
        this.directoryWatcher = directoryWatcher;
    }

    public void update(FileEvent ev)
    {
    }

    public void sync()
    {
    }
}
