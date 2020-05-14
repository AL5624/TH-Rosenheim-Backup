package training.lecture.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Discussion
{
    public static void main(String[] args)
    {
        Runnable rosenheim = new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    for(int i = 1; i <= 1000; i++)
                    {
                        System.out.println("Rosenheim is super! " + i);
                        Thread.sleep(10);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    return;
                }
            }
        };

        Runnable muenchen = () ->
        {
            try
            {
                for(int i = 1; i <= 1000; i++)
                {
                    System.out.println("Muenchen is super! " + i);
                    Thread.sleep(10);
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return;
            }
        };

        ExecutorService exe = Executors.newSingleThreadExecutor();
        exe.execute(rosenheim);
        exe.execute(muenchen);
        exe.shutdown();

        System.out.println("fertig");
    }
}
