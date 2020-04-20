package Reaktives_System;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

import static Reaktives_System.DirectoryWatcher.Zustandsuebergang;

public class WatchedFile
{
    /**Name der Datei*/
    private String        dateiname;
    /**Verzeichnis, in dem sich Datei befindet*/
    private Path          verzeichnis;
    /**Zeit der letzten synchronisation*/
    private LocalDateTime zeitstempel;
    /**derzeitiger Zustand*/
    private Zustand       state;

    private static LinkedList<WatchedFile> allWatchedFiles = new LinkedList<WatchedFile>();

   public enum Zustand
    {
        /**Lokal wurde eine neue Datei erzeugt[0]*/
        CREATED,
        /**Eine existierende (schon mal synchronisierte) Datei wurde lokal gelöscht[]1*/
        DELETED,
        /**das Verzeichnis und die synchronisierten Verzeichnisse sind synchron[2]*/
        INSYNC,
        /**Eine existierende (schon mal synchronisierte) Datei wurde lokal verändert. Die Datei wurde schon mal synchronisiert[3]*/
        MODIFIED,
        /**Die Datei ist endgültig gelöscht, z.B. nachdem sie lokal gelöscht und dann synchronisiert wurde oder sie wurde lokal erzeugt und dann gleich wieder gelöscht[4]*/
        GONE
    }

    public WatchedFile(String dateiname, Path verzeichnis)
    {
        this.dateiname = dateiname;
        this.verzeichnis = verzeichnis;
        zeitstempel = LocalDateTime.now();
        state = Zustand.CREATED;
    }

    public static LinkedList<WatchedFile> getAllWatchedFiles()
    {
        return allWatchedFiles;
    }

    public String getDateiname()
    {
        return dateiname;
    }

    public void setDateiname(String dateiname)
    {
        this.dateiname = dateiname;
    }

    public void setVerzeichnis(Path verzeichnis)
    {
        this.verzeichnis = verzeichnis;
    }

    /**Methode, um von einem Zustand in den naechsten zu gelangen*/
    public void setState(DirectoryWatcher.Symbol symbol)
    {
        state = Zustandsuebergang[state.ordinal()][symbol.ordinal()];
    }

    public Path getVerzeichnis()
    {
        return verzeichnis;
    }

    public LocalDateTime getZeitstempel()
    {
        return zeitstempel;
    }

    public Zustand getState()
    {
        return state;
    }


}
