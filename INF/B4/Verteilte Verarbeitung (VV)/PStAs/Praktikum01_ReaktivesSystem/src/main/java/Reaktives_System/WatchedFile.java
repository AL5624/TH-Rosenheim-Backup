package Reaktives_System;

import java.nio.file.Path;
import java.time.LocalDateTime;

import static Reaktives_System.DirectoryWatcher.state_transitions;

public class WatchedFile
{
    /**
     * name of the file
     */
    private String        name;
    /**
     * directory in which the file is located (only relevant for DirectoryWatcher)
     */
    private Path          directory;
    /**
     * last synchronization time
     */
    private LocalDateTime time_stamp;
    /**
     * current state
     */
    private State         state;

    public enum State
    {
        /**
         * a new file is created[0]
         */
        CREATED,
        /**
         * a existing already synchronized file is deleted[1]
         */
        DELETED,
        /**
         * file is in both directories synchronized[2]
         */
        INSYNC,
        /**
         * a existing already synchronized file is modified[3]
         */
        MODIFIED,
        /**
         * the file is for ever gone[4] for example
         * a new created file gets deleted or a deleted file gets synchronized
         */
        GONE
    }

    public WatchedFile(String name, Path directory)
    {
        this.name  = name;
        time_stamp = LocalDateTime.now();
        state      = State.CREATED;
        this.directory = directory;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDirectory(Path directory)
    {
        this.directory = directory;
    }

    public void setTime_stamp()
    {
        this.time_stamp = LocalDateTime.now();
    }

    /**
     * Method for moving from one state to the next
     */
    public void setState(DirectoryWatcher.Symbol symbol)
    {
        state = state_transitions[state.ordinal()][symbol.ordinal()];
    }

    public Path getDirectory()
    {
        return directory;
    }

    public LocalDateTime getTime_stamp()
    {
        return time_stamp;
    }

    public State getState()
    {
        return state;
    }


}
