package training.lecture.reactive;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class EchoWithLogging
{
    public static Logger LOGGER = Logger.getLogger(EchoWithLogging.class.getName());

    public static void main(String[] args)
    {
        try
        {
            Handler fileHandler = new FileHandler("echo.log");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.WARNING);
        }
        catch (IOException e)
        {
            System.err.println("Logfile " + "echo.log" + " not accessible");
            System.err.println("Please ask the administrator");
            System.exit(1);
        }

        try
        {
            LOGGER.info("Echo starting ...");

            LOGGER.info("Java Properties");
            LOGGER.info("Betriebssystem: " + System.getProperty("os.name"));
            LOGGER.info("Version des BS: " + System.getProperty("os.version"));
            LOGGER.info("Benutzer: " + System.getProperty("user.name"));
            LOGGER.info("Java Home: " + System.getProperty("java.home"));
            LOGGER.info("Java Version: " + System.getProperty("java.version"));
            LOGGER.info("Java hersteller: " + System.getProperty("java.vendor"));
            LOGGER.info("Class Path: " + System.getProperty("java.class.path"));

            Scanner scanner = new Scanner(System.in);
            while(true)
            {
                String line = scanner.nextLine();
                if(line.equals("Kill"))
                    throw new IllegalArgumentException("Kill not allowed");
                String output = "Echo: " + line;
                System.out.println(output);
            }
        }
        catch (IllegalArgumentException e)
        {
            LOGGER.log(Level.SEVERE, "Kill entered by user", e);
            LOGGER.info("Echo terminated with error");
            System.err.println("Program terminates due to an internal problem");
            System.err.println("Please ask th administrator");
            System.exit(1);
        }
        LOGGER.info("Echo terminated");
    }
}
