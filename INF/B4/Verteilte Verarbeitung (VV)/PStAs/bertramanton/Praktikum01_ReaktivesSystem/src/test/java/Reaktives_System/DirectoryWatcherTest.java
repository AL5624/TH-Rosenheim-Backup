package Reaktives_System;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

public class DirectoryWatcherTest
{
    //Has to be static because if we don't make it static the File in BeforeClass is created before the directory which makes the checkForEventsTests useless. But we made it static,
    //so the directoryWatcher is created before the File and we can test the method checkForEvents.
    //Also it is static so it doesn't create a log files for every test.
    static DirectoryWatcher directoryWatcher                            = new DirectoryWatcher();
    static Logger           directoryWatcherTestLogger_ErrorsExceptions = Logger.getLogger(DirectoryWatcherTest.class
                                                                                                   .getName());

    @BeforeClass
    public static void createFile()
    {
        try
        {
            //file handler for error and exceptions
            Handler fileHandlerTest_Error_Exceptions = new FileHandler("DirectoryWatcherTest_ErrorExceptions.log");
            fileHandlerTest_Error_Exceptions.setFormatter(new SimpleFormatter());
            directoryWatcherTestLogger_ErrorsExceptions.addHandler(fileHandlerTest_Error_Exceptions);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        File file = new File("testFile1.txt");
        try
        {
            file.createNewFile();
            Thread.sleep(500);
        }
        catch (Exception e)
        {
            directoryWatcherTestLogger_ErrorsExceptions.log(Level.INFO, "\"file.createNewFile() error\"");
        }
    }

    @Test
    public void DirectoryWatcherWithFilesAlreadyInDirectory_WatchedDirectoryGetAllWatchedFiles()
    {
        assertThat(directoryWatcher.getWatchedDirectory().getAllWatchedFiles()
                                   .get("Text File.txt")
                                   .getName(), equalTo("Text File.txt"));
    }

    @Test
    public void checkForEventsCreate()
    {
        directoryWatcher.CheckForEvents();
        assertThat(directoryWatcher.getWatchedDirectory().getAllWatchedFiles()
                                   .get("testFile1.txt")
                                   .getState(), equalTo(WatchedFile.State.CREATED));
    }

    @Test
    public void checkForEventsModify()
    {
        directoryWatcher.getWatchedDirectory()
                        .getAllWatchedFiles()
                        .get("testFile1.txt")
                        .setState(DirectoryWatcher.Symbol.SYNC);
        try
        {
            FileWriter myWriter = new FileWriter("testFile1.txt");
            myWriter.write("Test");
            myWriter.close();
            Thread.sleep(500);
        }
        catch (Exception e)
        {
            directoryWatcherTestLogger_ErrorsExceptions.log(Level.INFO, "\"FileWriter error\"");
        }
        directoryWatcher.CheckForEvents();
        assertThat(directoryWatcher.getWatchedDirectory().getAllWatchedFiles()
                                   .get("testFile1.txt")
                                   .getState(), equalTo(WatchedFile.State.MODIFIED));
    }

    @AfterClass
    public static void checkForEventsDelete()
    {
        File file = new File("testFile1.txt");
        try
        {
            file.delete();
            Thread.sleep(500);
        }
        catch (Exception e)
        {
            directoryWatcherTestLogger_ErrorsExceptions.log(Level.INFO, "\"file.createNewFile() error\"");
        }

        directoryWatcher.CheckForEvents();

        assertThat(directoryWatcher.getWatchedDirectory().getAllWatchedFiles()
                                   .get("testFile1.txt")
                                   .getState(), equalTo(WatchedFile.State.DELETED));
    }
}