package Reaktives_System;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.*;

public class DirectoryWatcher
{
    /**
     * array with state transitions [old state][symbol] (result = new state)
     */
    public static final WatchedFile.State[][] state_transitions                       = new WatchedFile.State[5][4];
    /**
     * directory which is observed
     */
    private             Path                  directory;
    /**
     * watchKey is needed to check events
     */
    private             WatchKey              watchKey;
    /**
     * Logger state transitions DirectoryWatcher
     */
    private             Logger                LOGGER_state_transitions                = Logger.getLogger(DirectoryWatcher.class
                                                                                                                 .getName() + "_stateTransitions");
    /**
     * Logger errors and exceptions DirectoryWatcher
     */
    private             Logger                directoryWatcherLogger_ErrorsExceptions = Logger.getLogger(DirectoryWatcher.class
                                                                                                                 .getName() + "_ErrorsExceptions");
    /**
     * the watchedDirectory class handles the map with all WatchedFiles in it
     */
    private             WatchedDirectory      watchedDirectory;

    public enum Symbol
    {
        /**
         * file has been created[0]
         */
        CREATE,
        /**
         * file has been deleted[1]
         */
        DELETED,
        /**
         * file has been synchronized[2]
         */
        SYNC,
        /**
         * file has been modified[3]
         */
        MODIFY
    }

    public DirectoryWatcher(String path)
    {
        Initialization_state_transitions();
        LoggerInitialization();
        directory = Paths.get(path);
        setWatchService_setWatchKey();
        watchedDirectory = new WatchedDirectory();
        ScanDirectory();
    }

    public DirectoryWatcher()
    {
        this(System.getProperty("user.dir"));
    }

    /**
     * initialization of the array state transitions
     */
    private void Initialization_state_transitions()
    {
        //initialization state transitions array; look at UML;
        //created --> created : CREATE
        state_transitions[0][0] = WatchedFile.State.CREATED;
        //created --> gone : DELETED
        state_transitions[0][1] = WatchedFile.State.GONE;
        //created --> insync : SYNC
        state_transitions[0][2] = WatchedFile.State.INSYNC;
        //created --> created : MODIFY
        state_transitions[0][3] = WatchedFile.State.CREATED;

        //deleted --> modified : CREATE
        state_transitions[1][0] = WatchedFile.State.MODIFIED;
        //deleted --> deleted : DELETED
        state_transitions[1][1] = WatchedFile.State.DELETED;
        //deleted --> gone : SYNC*/
        state_transitions[1][2] = WatchedFile.State.GONE;
        //deleted --> modified : MODIFY
        state_transitions[1][3] = WatchedFile.State.MODIFIED;

        //insync --> modified : CREATE
        state_transitions[2][0] = WatchedFile.State.MODIFIED;
        //insync --> deleted : DELETED
        state_transitions[2][1] = WatchedFile.State.DELETED;
        //insync --> insync : SYNC
        state_transitions[2][2] = WatchedFile.State.INSYNC;
        //insync --> modified : MODIFY
        state_transitions[2][3] = WatchedFile.State.MODIFIED;

        //modified --> modified : CREATE
        state_transitions[3][0] = WatchedFile.State.MODIFIED;
        //modified --> deleted : DELETED
        state_transitions[3][1] = WatchedFile.State.DELETED;
        //modified --> insync : SYNC
        state_transitions[3][2] = WatchedFile.State.INSYNC;
        //modified --> modified : MODIFY
        state_transitions[3][3] = WatchedFile.State.MODIFIED;

        //gone --> created : CREATE
        state_transitions[4][0] = WatchedFile.State.CREATED;
        //gone --> gone : DELETED
        state_transitions[4][1] = WatchedFile.State.GONE;
        //gone --> gone : SYNC
        state_transitions[4][2] = WatchedFile.State.GONE;
        //gone --> gone : MODIFY
        state_transitions[4][3] = WatchedFile.State.GONE;
    }

    /**
     * initialization of the "DirWatcher_state_transitions.log" and the "DirWatcher_Error_Exceptions.log"
     */
    private void LoggerInitialization()
    {
        try
        {
            //file handler for state_transitions
            Handler fileHandler_state_transitions = new FileHandler("DirectoryWatcher_stateTransitions.log");
            fileHandler_state_transitions.setFormatter(new SimpleFormatter());
            LOGGER_state_transitions.addHandler(fileHandler_state_transitions);

            //file handler for error and exceptions
            Handler fileHandler_Error_Exceptions = new FileHandler("DirectoryWatcher_ErrorExceptions.log");
            fileHandler_Error_Exceptions.setFormatter(new SimpleFormatter());
            directoryWatcherLogger_ErrorsExceptions.addHandler(fileHandler_Error_Exceptions);


        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * initialization of WatchService and WatchKey
     */
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
            directoryWatcherLogger_ErrorsExceptions.log(Level.SEVERE, "watchService/Key initialization failed", e);
        }
    }

    /**
     * while initialization of DirectoryWatcher, scans the directory for already existing files
     */
    private void ScanDirectory()
    {
        File   file  = new File(directory.toString());
        File[] files = file.listFiles();
        for (File f : files)
        {
            watchedDirectory.getAllWatchedFiles().put(f.getName(), new WatchedFile(f.getName(), directory));
            LOGGER_state_transitions.log(Level.INFO, "new WatchedFile " + Symbol.CREATE.toString() + "D: " + f
                    .getName());
        }
    }

    /**
     * Should only be used in method CheckForEvents().
     * Makes the method CheckForEvents() more compact and easier to read.
     *
     * @param event  is the WatchEvent from the WatchKey which observes the directory and gives us ENTRY_CREATE/MODIFY/DELETED
     * @param symbol is the symbol for our DirectoryWatcher and WatchedDirectory
     */
    private void EventAction(WatchEvent<?> event, Symbol symbol)
    {
        //if the file is created || if the file is modified/deleted but not in our allWatchedFiles Map
        if (symbol.equals(Symbol.CREATE) || !watchedDirectory.getAllWatchedFiles()
                                                             .containsKey(event.context().toString()))
        {
            //makes a new WatchedFile and adds it to our allWatchedFiles Map
            watchedDirectory.getAllWatchedFiles().put((event.context()).toString(),
                                                      new WatchedFile(event.context().toString(),
                                                                      directory.resolve((Path) event.context())));
        }
        //sets the new State
        watchedDirectory.getAllWatchedFiles().get(event.context().toString()).setState(symbol);
        //"new WatchedFile ENTRY_*** FileName"
        LOGGER_state_transitions.log(Level.INFO, "new WatchedFile " + event.kind()
                                                                           .toString() + ": " + event.context()
                                                                                                     .toString());
    }

    /**
     * check for event CREATE, DELETED or MODIFY
     */
    public void CheckForEvents()
    {
        for (WatchEvent<?> event : watchKey.pollEvents())
        {
            //file is CREATED
            if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE))
            {
                EventAction(event, Symbol.CREATE);
            }
            //file is DELETED
            else if (event.kind().equals(StandardWatchEventKinds.ENTRY_DELETE))
            {
                EventAction(event, Symbol.DELETED);
            }
            //file is MODIFIED
            else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY))
            {
                EventAction(event, Symbol.MODIFY);
            }
            //some strange kind of event occurred
            else
            {
                directoryWatcherLogger_ErrorsExceptions.log(Level.SEVERE, "event.kind() was not ENTRY_CREATE/DELETE/MODIFY. ERROR: " +
                                                                          event.kind().toString());
            }
        }
        watchKey.reset();
    }

    //getter and setter:

    public WatchedDirectory getWatchedDirectory()
    {
        return watchedDirectory;
    }

    public Path getDirectory()
    {
        return directory;
    }

    public static void main(String[] args)
    {

        DirectoryWatcher directoryWatcher = new DirectoryWatcher();
/*        try
        {
            FileWriter myWriter = new FileWriter("D:\\Eigene Dokumente\\Fh-Rosenheim\\TH-Rosenheim-Backup\\INF\\B4\\Verteilte Verarbeitung (VV)\\PStAs\\bertramanton\\tmp2\\Text File.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }*/

/*        DirectoryWatcher directoryWatcher = new DirectoryWatcher("D:\\Eigene Dokumente\\Fh-Rosenheim\\TH-Rosenheim-Backup\\INF\\B4\\Verteilte Verarbeitung (VV)\\PStAs\\bertramanton\\tmp");

        do
        {
            directoryWatcher.CheckForEvents();
        }
        while(directoryWatcher.getWatchedDirectory().getAllWatchedFiles().size() < 5);

        int i = 1;

        for(Map.Entry<String, WatchedFile> entry: directoryWatcher.getWatchedDirectory().getAllWatchedFiles().entrySet())
        {
            System.out.println("Watched File "+ i + ": " + entry.getKey());
            System.out.println(directoryWatcher.getWatchedDirectory().getAllWatchedFiles().get("Text File.txt").getName());
            ++i;
        }

        System.out.println("\n");

        for(i = 1; i < directoryWatcher.fileEvents.size(); i++)
        {
            System.out.println("File Event " + i + ": " + directoryWatcher.fileEvents.get(i-1).getName() + ", " + directoryWatcher.fileEvents.get(i - 1).getSymbol());
        }*/
    }
}
