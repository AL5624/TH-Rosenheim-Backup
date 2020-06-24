package training.lecture.network;

import com.sun.tools.jdeprscan.scan.Scan;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleHTTPClient {
  public static final String HOST = "www.th-rosenheim.de";
  public static final int HTTTP_PORT = 80;
  public static final String HTTP_MESSAGE = "GET / HTTP/1.1\r\nHost: " + HOST + "\r\n\r\n";

  public static void main(String[] args) {

    try (Socket webServer = new Socket(HOST, HTTTP_PORT)) {
      PrintStream out = new PrintStream(webServer.getOutputStream());
      out.print(HTTP_MESSAGE);
      out.flush();

      Scanner scan = new Scanner(webServer.getInputStream());
      String line = scan.nextLine();

      while (scan.hasNext()) {
        System.out.println(line);
        line = scan.nextLine();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
