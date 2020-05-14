package training.lecture.threads;

import java.util.concurrent.*;

public class ProducerConsumer
{
    public static void main(String[] args) throws Exception
    {
        BlockingQueue<String> orders = new ArrayBlockingQueue<>(5);
        ExecutorService exe = Executors.newCachedThreadPool();

        Runnable producer = () ->
        {
            try
            {
                for(int i = 1; i <= 30; i++)
                {
                    Thread.currentThread().sleep(1000);
                    System.out.println("Sending: Order ("+i+") ");
                    orders.put("Order ("+i+") ");
                }
                orders.put("EXIT");
            }
            catch (InterruptedException e)
            {
                return;
            }
        };
        exe.execute(producer);

        Runnable consumer = () ->
        {
            try
            {
                while(!Thread.currentThread().isInterrupted())
                {
                    Thread.currentThread().sleep(10);
                    String order = orders.take();
                    if(order.equals("EXIT"))break;
                    System.out.println("Receiving: " + order);
                }
            }
            catch (InterruptedException e)
            {
                return;
            }
        };
        exe.execute(consumer);
        exe.shutdown();
    }
}
