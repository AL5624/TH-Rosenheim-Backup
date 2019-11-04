import java.util.Scanner;

public class TestEmployee {


    public static float BerechnungDurchschnittsgehalt (Employee [] s){

        float a = 0.0f;

        for (int i = 0; i < 3; i++){

            a += s[i].getMonatsgehalt();

        }

        float durchschnittsgehalt = a / 3;

        return  Math.round(durchschnittsgehalt);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Employee Angestellter_1 = new Employee("Anton", 50000f);

        Employee Angestellter_2 = new Employee("Paul", 40000f);

        Employee Angestellter_3 = new Employee("Moritz");

        Employee [] Mitarbeiter = new Employee[10];

        Mitarbeiter [0] = Angestellter_1;
        Mitarbeiter[1] = Angestellter_2;
        Mitarbeiter[2] = Angestellter_3;

        float a;

        System.out.println("Wie viel soll " + Angestellter_3.getName() + " verdienen?");
        a = scanner.nextFloat();

       Angestellter_3.setGehaltsänderung(a);

       float b = BerechnungDurchschnittsgehalt(Mitarbeiter);

       System.out.println("Durchschnittsgehalt: " + b);

       System.out.print("\n"+ TestRandom());

    }

    public static int TestRandom(){

        int b = 0;

        for(int i = 0; i < 100000; i++){
            double a = Math.random();
            if(a > 0.25) {
                ++b;
            }
        }

        return b;
    }

}
