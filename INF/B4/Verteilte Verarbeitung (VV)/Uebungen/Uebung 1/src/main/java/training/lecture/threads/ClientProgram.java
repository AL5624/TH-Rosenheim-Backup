package training.lecture.threads;

import java.util.concurrent.*;

public class ClientProgram {
  public static void main(String[] args) throws Exception {
    SlowServer slow = new SlowServer();

    ExecutorService exe = Executors.newSingleThreadExecutor();

    for (int i = 1; i <= 20; i++) {
      String input = "text: " + i;
      System.out.println("Start     :" + i);

      CompletableFuture<Void> serverCall = CompletableFuture.supplyAsync(() ->
      {
        String result = slow.veryWichtigServerCall(input);
        return result;
      }, exe)
          .thenAccept((s) -> {
            System.out.println("Ergebnis = " + s);
          });

/*            Future<String> futureResult = exe.submit(serverCall);

            //Hier kann ich noch was machen

            while(!futureResult.isDone())
            {
                Thread.currentThread().sleep(1000);
                System.out.println("Poll");
            }*/

//            System.out.println("Berechnet: " + futureResult.get(1, TimeUnit.SECONDS));
      System.out.println("Ende     : " + i + "\n");
    }
  }
}
