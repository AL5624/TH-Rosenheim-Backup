package training.lecture.streams;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NummerFilter extends FilterOutputStream {
  /**
   * Creates an output stream filter built on top of the specified
   * underlying output stream.
   *
   * @param out the underlying output stream to be assigned to
   *            the field {@code this.out} for later use, or
   *            <code>null</code> if this instance is to be
   *            created without an underlying stream.
   */
  public NummerFilter(OutputStream out) {
    super(out);
  }

  @Override
  public void write(int b) throws IOException {
      if ((char) b == '1') {
          super.write("eins ".getBytes());
      } else {
          super.write(b);
      }
  }

  public static void main(String[] args) {
    try {
      NummerFilter filter = new NummerFilter(System.out);
      filter.write("Hallo Welt 12345\n".getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
