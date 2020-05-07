package training.lecture.serialize;

import java.io.PrintStream;
import java.util.Scanner;

public interface CSVSerializer
{
    void readFrom(Scanner in);

    void writeTo(PrintStream out);
}
