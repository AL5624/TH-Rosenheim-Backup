package Reaktives_System;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class WatchedDirectoryTest
{
    static WatchedDirectory watchedDirectory                            = new WatchedDirectory();
    static DirectoryWatcher directoryWatcher                            = new DirectoryWatcher(watchedDirectory);
    static Logger           watchedDirectoryTestLogger_ErrorsExceptions = Logger.getLogger(WatchedDirectoryTest.class
                                                                                                   .getName());

    @BeforeClass
    public static void setUp()
    {
        try
        {
            //file handler for error and exceptions
            Handler fileHandlerTest_Error_Exceptions = new FileHandler("WatchedDirectoryTest_ErrorExceptions.log");
            fileHandlerTest_Error_Exceptions.setFormatter(new SimpleFormatter());
            watchedDirectoryTestLogger_ErrorsExceptions.addHandler(fileHandlerTest_Error_Exceptions);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Test
    public void update()
    {
        directoryWatcher.getWatchedDirectory().update(new FileEvent("Text File.txt", DirectoryWatcher.Symbol.SYNC));
        assertThat(directoryWatcher.getWatchedDirectory()
                                   .getAllWatchedFiles()
                                   .get("Text File.txt")
                                   .getState(), equalTo(WatchedFile.State.INSYNC));
    }

    @Test
    public void sync()
    {
        directoryWatcher.getWatchedDirectory().sync(System.out);
        assertThat(directoryWatcher.getWatchedDirectory()
                                   .getAllWatchedFiles()
                                   .get(".gradle")
                                   .getState(), equalTo(WatchedFile.State.INSYNC));
    }
}