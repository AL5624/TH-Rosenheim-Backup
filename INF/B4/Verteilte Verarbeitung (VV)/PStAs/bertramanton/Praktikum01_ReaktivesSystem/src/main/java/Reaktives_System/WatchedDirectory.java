package Reaktives_System;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
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
    private Map<String, WatchedFile> allWatchedFiles = new HashMap<>();

    /***/
    private Logger watchedDirectoryLogger_ErrorsExceptions = Logger.getLogger(DirectoryWatcher.class
                                                                                      .getName());

    private Gson gson;
    private JsonSerializer<LocalDateTime> localDateTimeJsonSerializer;
    private JsonSerializer<Path> pathJsonSerializer;
    private JsonDeserializer<LocalDateTime> localDateTimeJsonDeserializer;
    private JsonDeserializer<Path> pathJsonDeserializer;

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
        localDateTimeJsonSerializer = new JsonSerializer<LocalDateTime>()
        {
            @Override
            public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context)
            {
                if (src != null)
                {
                    return new JsonPrimitive(src.toString());
                }
                return null;
            }
        };

        pathJsonSerializer = new JsonSerializer<Path>()
        {
            @Override
            public JsonElement serialize(Path src, Type typeOfSrc, JsonSerializationContext context)
            {
                if (src != null)
                {
                    return new JsonPrimitive(src.toString());
                }
                return null;
            }
        };

        localDateTimeJsonDeserializer = new JsonDeserializer<LocalDateTime>()
        {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
            {
                if (json != null)
                {
                    return LocalDateTime.parse(json.getAsString());
                }
                return null;
            }
        };

        pathJsonDeserializer = new JsonDeserializer<Path>()
        {
            @Override
            public Path deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
            {
                if (json != null)
                {
                    return Paths.get(json.getAsString());
                }
                return null;
            }
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

    private void syncToAllWatchedFiles()
    {
        for (Map.Entry<String, WatchedFile> entry : allWatchedFiles.entrySet())
        {
            entry.getValue().setState(DirectoryWatcher.Symbol.SYNC);
        }
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


    //getter & setter:

    public Map<String, WatchedFile> getAllWatchedFiles()
    {
        return allWatchedFiles;
    }
}
