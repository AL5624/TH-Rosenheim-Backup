package Reaktives_System;

import com.google.gson.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.*;

public class WatchedDirectory
{
    /**
     * Map<name, watchedFile> for all watched Files
     */
    private final Map<String, WatchedFile> allWatchedFiles = new HashMap<>();

    /***/
    private final Logger watchedDirectoryLogger_ErrorsExceptions = Logger.getLogger(DirectoryWatcher.class
                                                                                            .getName());

    private Gson gson;

    public WatchedDirectory()
    {
        LoggerInitialization();
        GsonInitialization();
    }

    private void LoggerInitialization()
    {
        try
        {
            //file handler for error and exceptions
            Handler fileHandler_ErrorExceptions = new FileHandler("WatchedDirectory_ErrorExceptions.log");
            fileHandler_ErrorExceptions.setFormatter(new SimpleFormatter());
            watchedDirectoryLogger_ErrorsExceptions.addHandler(fileHandler_ErrorExceptions);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void GsonInitialization()
    {
        JsonSerializer<LocalDateTime> localDateTimeJsonSerializer = (src, typeOfSrc, context) ->
        {
            if (src != null)
            {
                return new JsonPrimitive(src.toString());
            }
            return null;
        };

        JsonSerializer<Path> pathJsonSerializer = (src, typeOfSrc, context) ->
        {
            if (src != null)
            {
                return new JsonPrimitive(src.toString());
            }
            return null;
        };

        JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer = (json, typeOfT, context) ->
        {
            if (json != null)
            {
                return LocalDateTime.parse(json.getAsString());
            }
            return null;
        };

        JsonDeserializer<Path> pathJsonDeserializer = (json, typeOfT, context) ->
        {
            if (json != null)
            {
                return Paths.get(json.getAsString());
            }
            return null;
        };

        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonSerializer)
                .registerTypeHierarchyAdapter(Path.class, pathJsonSerializer)
                .registerTypeAdapter(LocalDateTime.class, localDateTimeJsonDeserializer)
                .registerTypeHierarchyAdapter(Path.class, pathJsonDeserializer)
                .create();
    }

    public void update(FileEvent ev)
    {
        allWatchedFiles.get(ev.getName()).setState(ev.getSymbol());
    }

    public void sync(OutputStream out)
    {
        try
        {
            for (Map.Entry<String, WatchedFile> entry : allWatchedFiles.entrySet())
            {

                out.write(gson.toJson(entry.getValue(), WatchedFile.class).getBytes());
                System.out.println();
            }
        }
        catch (IOException e)
        {
            watchedDirectoryLogger_ErrorsExceptions.log(Level.INFO, "Error while writing to output stream");
        }

        syncToAllWatchedFiles();
    }

    private void syncToAllWatchedFiles()
    {
        for (Map.Entry<String, WatchedFile> entry : allWatchedFiles.entrySet())
        {
            entry.getValue().setState(DirectoryWatcher.Symbol.SYNC);
            entry.getValue().setTime_stamp();
        }
    }


    //getter & setter:

    public Map<String, WatchedFile> getAllWatchedFiles()
    {
        return allWatchedFiles;
    }
}
