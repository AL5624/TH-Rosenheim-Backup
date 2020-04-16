package training.lecture.reactive;

import java.util.Scanner;

public class Echo
{
    public static void main(String[] args)
    {
        //Eingabe

        Scanner scanner = new Scanner(System.in);

        while (true)
        {

            String line = scanner.nextLine();
            if(line.equals("Exit")) break;

            //Verarbeitung

            String output = "Echo: " + line;

            //Ausgabe

            System.out.println(output);

        }
    }
}
