package training.lecture.threads;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Terminator
{
    public static void main(String[] args)
    {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e)
            {
                System.out.println("Kaputt: " + t + "/" + e);
                System.exit(1);
            }
        });


        Runnable liesmalwas = () ->
        {
            Scanner scan  = new Scanner(System.in);
            String  input = "";

            while(!input.equals("EXIT") && !Thread.currentThread().isInterrupted())
            {
                input = scan.nextLine();
                System.out.println("Echo: " + input);
                if(input.equals("BUMM")) throw new RuntimeException("BUMM!");
            }
            if (Thread.currentThread().isInterrupted())
                System.out.println("dont interrupt me");
        };


        ExecutorService exe = Executors.newFixedThreadPool(10);
        exe.execute(liesmalwas);
        try
        {
            Thread.sleep(20000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        exe.shutdownNow();
    }
}
