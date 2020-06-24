package training.lecture.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServer {
  public static final Logger LOGGER = Logger.getLogger("EchoServer");
  public static final int PORT = 1024;
  private static final ExecutorService exe = Executors.newFixedThreadPool(10);

  public static void main(String[] args) {

    try (ServerSocket server = new ServerSocket(PORT)) {
      LOGGER.log(Level.INFO, "Server started on Port " + PORT);


      while (!Thread.currentThread().isInterrupted()) {
        Socket client = server.accept();
        exe.execute(() -> {
          handleClient(client, server);
        });
      }
    } catch (IOException e) {
      LOGGER.log(Level.INFO, e.getMessage(), e);
    }
    exe.shutdownNow();
    LOGGER.log(Level.INFO, "Server Terminated");

  }

  private static void handleClient(Socket client, ServerSocket server) {
    try (
        InputStream fromClientStream = client.getInputStream();
        Scanner fromClient = new Scanner(fromClientStream);
        OutputStream toClientStream = client.getOutputStream();
        PrintStream toClient = new PrintStream(toClientStream);) {
      LOGGER.log(Level.INFO, "Client acceptedon Port " + client.getPort());


      toClient.println("Hello World");

      while (fromClient.hasNext() && !Thread.currentThread().isInterrupted()) {
        String zeile = fromClient.nextLine();
        if (zeile.contains("EXIT")) {
          break;
        } else if (zeile.contains("SHUTDOWN")) {
          exe.shutdownNow();
          server.close();
          break;
        }
        toClient.println("Echo: " + zeile);
      }
    } catch (IOException e) {
      LOGGER.log(Level.INFO, e.getMessage(), e);
    }
  }
}
