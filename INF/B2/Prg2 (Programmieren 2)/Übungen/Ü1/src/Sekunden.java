import java.util.Scanner;

public class Sekunden {
    public static void main(String[] args){
        double stunden, minuten, sekunden;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Stunden eingeben: ");
        stunden = scanner.nextDouble();
        System.out.println("Bitte Minuten eingeben: ");
        minuten = scanner.nextDouble();

        sekunden = (stunden * 60 + minuten) * 60;

        System.out.println("Sekunden = " + sekunden);




    }
}
