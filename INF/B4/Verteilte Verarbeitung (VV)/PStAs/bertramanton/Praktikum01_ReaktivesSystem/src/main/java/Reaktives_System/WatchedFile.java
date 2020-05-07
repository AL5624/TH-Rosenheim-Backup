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
         * Lokal wurde eine neue Datei erzeugt[0]
         */
        CREATED,
        /**
         * Eine existierende (schon mal synchronisierte) Datei wurde lokal gelöscht[]1
         */
        DELETED,
        /**
         * das Verzeichnis und die synchronisierten Verzeichnisse sind synchron[2]
         */
        INSYNC,
        /**
         * Eine existierende (schon mal synchronisierte) Datei wurde lokal verändert. Die Datei wurde schon mal synchronisiert[3]
         */
        MODIFIED,
        /**
         * Die Datei ist endgültig gelöscht, z.B. nachdem sie lokal gelöscht und dann synchronisiert wurde oder sie wurde lokal erzeugt und dann gleich wieder gelöscht[4]
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
