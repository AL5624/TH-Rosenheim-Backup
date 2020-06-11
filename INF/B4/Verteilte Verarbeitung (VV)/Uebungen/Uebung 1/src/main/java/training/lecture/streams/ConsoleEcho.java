package training.lecture.streams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConsoleEcho {

  public static final int EOF = -1;

  public static void main(String[] args) {
    try (InputStream in = System.in;
         /*OutputStream out = new FileOutputStream("test.txt")*/OutputStream out = System.out) {
      int c = 0;

      while ((c = in.read()) != EOF) {
        out.write(c);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
