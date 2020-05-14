package training.lecture.threads;

public class SlowServer
{
    public String veryWichtigServerCall(String something)
    {
        try
        {
            Thread.currentThread().sleep(5000);
            return something.toUpperCase();
        }
        catch (InterruptedException e)
        {
            return "Inerrupted";
        }
    }
}
