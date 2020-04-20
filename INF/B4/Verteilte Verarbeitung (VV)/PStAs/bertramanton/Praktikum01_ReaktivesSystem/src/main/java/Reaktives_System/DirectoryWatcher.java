package Reaktives_System;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class DirectoryWatcher
{
    /**Array mit Zustandsuebergaengen [Alter Zustand][Symbol] (Ergebnis = neuer Zustand)*/
    public static final WatchedFile.Zustand[][] Zustandsuebergang = new WatchedFile.Zustand[5][4];
    private             WatchService            watchService;
    private             Path                    directory;
    private             WatchKey                watchKey;
    public static       Logger                  LOGGER = Logger.getLogger(DirectoryWatcher.class.getName());

    public enum Symbol
    {
        /**Datei wurde erzeugt[0]*/
        CREATE,
        /**Datei wurde gelöscht[1]*/
        DELETE,
        /**Datei wurde synchronisiert[2]*/
        SYNC,
        /**Datei wurde geändert[3]*/
        MODIFY
    }

    public DirectoryWatcher(String path)
    {
        if(path.equals(null)) path = System.getProperty("user.dir");

        directory = Paths.get(path);

        try
        {
            watchService = FileSystems.getDefault().newWatchService();

            watchKey = directory.register(watchService,
                                          StandardWatchEventKinds.ENTRY_CREATE,
                                          StandardWatchEventKinds.ENTRY_DELETE,
                                          StandardWatchEventKinds.ENTRY_MODIFY);
        }
        catch (IOException e)
        {
            LOGGER.log(Level.SEVERE, "watchService/Key initialization failed", e);
        }

        //initialisierung Zustanesuebergangs Array; siehe UML;
        /**created --> created : CREATE*/
        Zustandsuebergang [0][0] = WatchedFile.Zustand.CREATED;
        /**created --> gone : DELETED*/
        Zustandsuebergang [0][1] = WatchedFile.Zustand.GONE;
        /**created --> insync : SYNC*/
        Zustandsuebergang [0][2] = WatchedFile.Zustand.INSYNC;
        /**created --> created : MODIFY*/
        Zustandsuebergang [0][3] = WatchedFile.Zustand.CREATED;

        /**deleted --> modified : CREATE*/
        Zustandsuebergang [1][0] = WatchedFile.Zustand.MODIFIED;
        /**deleted --> deleted : DELETED*/
        Zustandsuebergang [1][1] = WatchedFile.Zustand.DELETED;
        /**deleted --> gone : SYNC*/
        Zustandsuebergang [1][2] = WatchedFile.Zustand.GONE;
        /**deleted --> modified : MODIFY*/
        Zustandsuebergang [1][3] = WatchedFile.Zustand.MODIFIED;

        /**insync --> modified : CREATE*/
        Zustandsuebergang [2][0] = WatchedFile.Zustand.MODIFIED;
        /**insync --> deleted : DELETED*/
        Zustandsuebergang [2][1] = WatchedFile.Zustand.DELETED;
        /**insync --> insync : SYNC*/
        Zustandsuebergang [2][2] = WatchedFile.Zustand.INSYNC;
        /**insync --> modified : MODIFY*/
        Zustandsuebergang [2][3] = WatchedFile.Zustand.MODIFIED;

        /**modified --> modified : CREATE*/
        Zustandsuebergang [3][0] = WatchedFile.Zustand.MODIFIED;
        /**modified --> deleted : DELETED*/
        Zustandsuebergang [3][1] = WatchedFile.Zustand.DELETED;
        /**modified --> insync : SYNC*/
        Zustandsuebergang [3][2] = WatchedFile.Zustand.INSYNC;
        /**modified --> modified : MODIFY*/
        Zustandsuebergang [3][3] = WatchedFile.Zustand.MODIFIED;

        /**gone --> created : CREATE*/
        Zustandsuebergang [4][0] = WatchedFile.Zustand.CREATED;
        /**gone --> gone : DELETED*/
        Zustandsuebergang [4][1] = WatchedFile.Zustand.GONE;
        /**gone --> gone : SYNC*/
        Zustandsuebergang [4][2] = WatchedFile.Zustand.GONE;
        /**gone --> gone : MODIFY*/
        Zustandsuebergang [4][3] = WatchedFile.Zustand.GONE;
    }

    public void CheckForEvents()
    {
        LinkedList<WatchedFile> tmp2 = WatchedFile.getAllWatchedFiles();
        for(WatchEvent<?> event: watchKey.pollEvents())
        {
            if(event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE))
            {
                WatchedFile  watchedFile = new WatchedFile(event.context().toString(), directory.resolve((Path) event.context()));
                if(WatchedFile.getAllWatchedFiles().contains(watchedFile) == false)
                {
                    WatchedFile.getAllWatchedFiles().add(watchedFile);
                    LOGGER.log(Level.SEVERE, "new WatchedFile created " + ((Path) event.context()).toString());
                }
            }
            else
            {
                for(int i = 0; i < tmp2.size(); i++)
                {
                    if(WatchedFile.getAllWatchedFiles().get(i).getDateiname().equals(event.context().toString()))
                    {
                        if (event.kind().equals(StandardWatchEventKinds.ENTRY_DELETE))
                        {
                            WatchedFile tmp = WatchedFile.getAllWatchedFiles().get(i);
                            tmp.setState(Symbol.DELETE);
                            LOGGER.log(Level.SEVERE, "WatchedFile deleted " + event.context().toString());
                            break;
                        }
                        else if(event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY))
                        {
                            WatchedFile tmp = WatchedFile.getAllWatchedFiles().get(i);
                            tmp.setState(Symbol.MODIFY);
                            LOGGER.log(Level.SEVERE, "WatchedFile modified " + event.context().toString());
                            break;
                        }
                        else break;
                    }
                }
            }
        }

        watchKey.reset();
    }


    public static void main(String[] args)
    {
            try
    {
        Handler fileHandler = new FileHandler("DirWatcher.log");
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }
        catch (
    IOException e)
    {
        System.err.println("Logfile initialization error");
    }

        Scanner          scanner          = new Scanner(System.in);

        DirectoryWatcher directoryWatcher = new DirectoryWatcher("I:\\tmp2\\");
        while(true)
        {
            directoryWatcher.CheckForEvents();
            if(WatchedFile.getAllWatchedFiles().size() > 5)break;
        }

        if(WatchedFile.getAllWatchedFiles().size() > 0)
        {
            for(int i = 0; i < WatchedFile.getAllWatchedFiles().size(); i++)
            {
                System.out.println(WatchedFile.getAllWatchedFiles().get(i).getDateiname());
            }
        }

    }

}
