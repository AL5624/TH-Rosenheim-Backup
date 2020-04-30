package Reaktives_System;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.*;

public class DirectoryWatcher
{
    /**array with state transitions [old state][symbol] (result = new state)*/
    public static final    WatchedFile.State[][]    state_transitions               = new WatchedFile.State[5][4];
    /**directory which is observed*/
    private                Path                     directory;
    /**watchKey is needed to check events*/
    private                WatchKey                 watchKey;
    /**Logger state transitions DirectoryWatcher*/
    private                Logger                   LOGGER_state_transitions        = Logger.getLogger(DirectoryWatcher.class.getName());
    /**Logger errors and exceptions DirectoryWatcher*/
    private                Logger                   LOGGER_Errors_Exceptions        = Logger.getLogger(DirectoryWatcher.class.getName());
    /**a map with all WatchedFiles*/
    private                Map<String, WatchedFile> allWatchedFilesDirectoryWatcher = new HashMap<>();
    /**a list with all fileEvents*/
    private                LinkedList<FileEvent>    fileEvents                      = new LinkedList<>();

    public enum Symbol
    {
        /**file has been created[0]*/
        CREATE,
        /**file has been deleted[1]*/
        DELETED,
        /**file has been synchronized[2]*/
        SYNC,
        /**file has been modified[3]*/
        MODIFY
    }

    public DirectoryWatcher(String path)
    {
        Initialization_state_transitions();
        LoggerInitialization();
        directory = Paths.get(path);
        setWatchService_setWatchKey();
        ScanDirectory();
    }

    public DirectoryWatcher()
    {
        this(System.getProperty("user.dir"));
    }

    private void Initialization_state_transitions()
    {
        //initialization state transitions array; look at UML;
        /**created --> created : CREATE*/
        state_transitions[0][0] = WatchedFile.State.CREATED;
        /**created --> gone : DELETED*/
        state_transitions[0][1] = WatchedFile.State.GONE;
        /**created --> insync : SYNC*/
        state_transitions[0][2] = WatchedFile.State.INSYNC;
        /**created --> created : MODIFY*/
        state_transitions[0][3] = WatchedFile.State.CREATED;

        /**deleted --> modified : CREATE*/
        state_transitions[1][0] = WatchedFile.State.MODIFIED;
        /**deleted --> deleted : DELETED*/
        state_transitions[1][1] = WatchedFile.State.DELETED;
        /**deleted --> gone : SYNC*/
        state_transitions[1][2] = WatchedFile.State.GONE;
        /**deleted --> modified : MODIFY*/
        state_transitions[1][3] = WatchedFile.State.MODIFIED;

        /**insync --> modified : CREATE*/
        state_transitions[2][0] = WatchedFile.State.MODIFIED;
        /**insync --> deleted : DELETED*/
        state_transitions[2][1] = WatchedFile.State.DELETED;
        /**insync --> insync : SYNC*/
        state_transitions[2][2] = WatchedFile.State.INSYNC;
        /**insync --> modified : MODIFY*/
        state_transitions[2][3] = WatchedFile.State.MODIFIED;

        /**modified --> modified : CREATE*/
        state_transitions[3][0] = WatchedFile.State.MODIFIED;
        /**modified --> deleted : DELETED*/
        state_transitions[3][1] = WatchedFile.State.DELETED;
        /**modified --> insync : SYNC*/
        state_transitions[3][2] = WatchedFile.State.INSYNC;
        /**modified --> modified : MODIFY*/
        state_transitions[3][3] = WatchedFile.State.MODIFIED;

        /**gone --> created : CREATE*/
        state_transitions[4][0] = WatchedFile.State.CREATED;
        /**gone --> gone : DELETED*/
        state_transitions[4][1] = WatchedFile.State.GONE;
        /**gone --> gone : SYNC*/
        state_transitions[4][2] = WatchedFile.State.GONE;
        /**gone --> gone : MODIFY*/
        state_transitions[4][3] = WatchedFile.State.GONE;
    }

    private void setWatchService_setWatchKey()
    {
        try
        {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            watchKey = directory.register(watchService,
                                          StandardWatchEventKinds.ENTRY_CREATE,
                                          StandardWatchEventKinds.ENTRY_DELETE,
                                          StandardWatchEventKinds.ENTRY_MODIFY);
        }
        catch (IOException e)
        {
            LOGGER_Errors_Exceptions.log(Level.SEVERE, "watchService/Key initialization failed", e);
        }
    }

    /**check for event CREATE, DELETED or MODIFY*/
    public void CheckForEvents()
    {
        for(WatchEvent<?> event: watchKey.pollEvents())
        {
            //file is CREATED
            if(event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE))
            {
                WatchedFile watchedFile = new WatchedFile(event.context()
                                                               .toString(), directory.resolve((Path) event.context()));
                allWatchedFilesDirectoryWatcher.put(watchedFile.getName(), watchedFile);
                FileEvent fileEvent = new FileEvent(watchedFile.getName(), Symbol.CREATE);
                fileEvents.add(fileEvent);
                //"new WatchedFile ENTRY_CREATE FileName"
                LOGGER_state_transitions.log(Level.SEVERE, "new WatchedFile " + event.kind()
                                                                                     .toString() + ": " + ((Path) event.context()).toString());
            }
            //file is DELETED
            else if (event.kind().equals(StandardWatchEventKinds.ENTRY_DELETE))
            {
                allWatchedFilesDirectoryWatcher.get(event.context().toString()).setState(Symbol.DELETED);
                FileEvent fileEvent = new FileEvent(event.context().toString(), Symbol.DELETED);
                fileEvents.add(fileEvent);
                //"WatchedFile ENTRY_DELETE FileName"
                LOGGER_state_transitions.log(Level.SEVERE, "WatchedFile " + event.kind().toString() + ": " + event.context().toString());
            }
            //file is MODIFIED
            else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY))
            {
                allWatchedFilesDirectoryWatcher.get(event.context().toString()).setState(Symbol.MODIFY);
                FileEvent fileEvent = new FileEvent(event.context().toString(), Symbol.MODIFY);
                fileEvents.add(fileEvent);
                //"WatchedFile ENTRY_MODIFY FileName"
                LOGGER_state_transitions.log(Level.SEVERE, "WatchedFile " + event.kind().toString() + ": " + event.context().toString());
            }
            else
            {
                LOGGER_state_transitions.log(Level.SEVERE, "event.kind() was not ENTRY_CREATE/DELETE/MODIFY. ERROR: " + event.kind().toString());
            }
        }
        watchKey.reset();
    }

    /**while initialization of DirectoryWatcher, scans the directory for already existing files*/
    private void ScanDirectory()
    {
        File file = new File(directory.toString());
        File[] files = file.listFiles();
        for(File f: files)
        {
            WatchedFile watchedFile = new WatchedFile(f.getName(), directory);
            FileEvent fileEvent = new FileEvent(watchedFile.getName(), Symbol.CREATE);
            allWatchedFilesDirectoryWatcher.put(watchedFile.getName(), watchedFile);
            fileEvents.add(fileEvent);
            LOGGER_state_transitions.log(Level.SEVERE, "new WatchedFile " + Symbol.CREATE.toString() + "D: " + watchedFile.getName());
        }
    }

    public LinkedList<FileEvent> getFileEvents()
    {
        return fileEvents;
    }

    public Map<String, WatchedFile> getAllWatchedFilesDirectoryWatcher()
    {
        return allWatchedFilesDirectoryWatcher;
    }

    private void LoggerInitialization()
    {
        try
        {
            /**file handler for state_transitions*/
            Handler fileHandler_state_transitions = new FileHandler("DirWatcher_state_transitions.log");
            fileHandler_state_transitions.setFormatter(new SimpleFormatter());
            LOGGER_state_transitions.addHandler(fileHandler_state_transitions);

            /**file handler for error and exceptions*/
            Handler fileHandler_Error_Exceptions = new FileHandler("DirWatcher_Error_Exceptions.log");
            fileHandler_Error_Exceptions.setFormatter(new SimpleFormatter());
            LOGGER_Errors_Exceptions.addHandler(fileHandler_Error_Exceptions);


        }
        catch (IOException e)
        {
            System.err.println("Logfile initialization error");
            System.exit(1);
        }
    }

    public static void main(String[] args)
    {
        //Habe erstmal die main benutzt, um die Methode CheckForEvents() zu testen (Richtige Tests kommen noch)

        DirectoryWatcher directoryWatcher = new DirectoryWatcher("I:\\tmp2\\");

        do
        {
            directoryWatcher.CheckForEvents();
        }while(directoryWatcher.getAllWatchedFilesDirectoryWatcher().size() < 5);

        int i = 1;
        for(Map.Entry<String, WatchedFile> entry: directoryWatcher.getAllWatchedFilesDirectoryWatcher().entrySet())
        {
            System.out.println("Watched File "+ i + ": " + entry.getKey());
            ++i;
        }

        System.out.println("\n");

        for(i = 1; i < directoryWatcher.fileEvents.size(); i++)
        {
            System.out.println("File Event " + i + ": " + directoryWatcher.fileEvents.get(i-1).getName() + ", " + directoryWatcher.fileEvents.get(i - 1).getSymbol());
        }
    }
}
