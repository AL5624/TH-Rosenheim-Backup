/**
 * Created by Wolfgang Mühlbauer on 01.04.2016.
 */
public class Test {

    public static void main(String[] args) {
        testConstructors();
        testArithmetic();

    }

    public static void testConstructors() {

        System.out.println("Testing constructors ... Observed issues:");

        Rational r1 = new Rational();
        if (r1.getNumerator() != 0 || r1.getDenominator() != 1) {
            System.out.println("Error with r1");
        };
        Rational r2 = new Rational(1, 2);
        if (r2.getNumerator() != 1 || r2.getDenominator() != 2) {
            System.out.println("Error with r2");
        }
        Rational r3 = new Rational(5, -15);
        if (r3.getNumerator() != -1 || r3.getDenominator() != 3) {
            System.out.println("Error with r3");
        }
        Rational r4 = new Rational(3,6);
        if (r4.getNumerator() != 1 || r4.getDenominator() != 2) {
            System.out.println("Error with r4");
        }
        Rational r5 = new Rational(2);
        if (r5.getNumerator() != 2 || r5.getDenominator() != 1) {
            System.out.println("Error with r5");
        }
        Rational r6= new Rational(3.1415);
        if (r6.getNumerator() != 6283 || r6.getDenominator() != 2000) {
            System.out.println("Error with r6");
        }
        Rational r7 = new Rational(2.5);
        if (r7.getNumerator() != 5 || r7.getDenominator() != 2) {
            System.out.println("Error with r7");
        }
        Rational r8 = new Rational("1/2");
        if (r8.getNumerator() != 1 || r8.getDenominator() != 2) {
            System.out.println("Error with r8");
        }
        Rational r9 = new Rational("4/-6");
        if (r9.getNumerator() != -2 || r9.getDenominator() != 3) {
            System.out.println("Error with r9");
        }

    }


    public static void testArithmetic() {

        System.out.println("Some calculations ... Observed issues:");

        // test multiplication
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(2, 3);
        Rational r3 = r1.multiply(r2); // 1/2 * 2/3 = 2/6 (1/3)
        if (r3.getNumerator() != 1 || r3.getDenominator() != 3) {
            System.out.println("multiplication error");
        }

        // test inverting
        Rational r4 = r1.invert(); // 2/1
        if (r4.getNumerator() != 2 || r4.getDenominator() != 1) {
            System.out.println("invert error");
        }

        // test division
        Rational r5 = r1.divide(r2); // 1/2 * 3/2 = 3/4
        if (r5.getNumerator() != 3 || r5.getDenominator() != 4) {
            System.out.println("division error");
        }

        // test addition
        Rational r6 = r1.add(r2); // 1/2 + 2/3 = 7/6
        if (r6.getNumerator() != 7 || r6.getDenominator() != 6) {
            System.out.println("addition error");
        }

        // test subtraction
        Rational r7 = r1.subtract(r2); // 1/2 - 2/3 = -1/6
        if (r7.getNumerator() != -1 || r7.getDenominator() != 6) {
            System.out.println("subtraction error");
        }

        // test negation
        Rational r8 = r1.negate(); // -1/2
        if (r8.getNumerator() != -1 || r8.getDenominator() != 2) {
            System.out.println("negation error");
        }

        double d1 = r5.doubleValue();   // r5 == 3/4 = 0.75
        if (d1 >= 0.750001 || d1 <= 0.749999) {
            System.out.println("possible issue with method doubleValue()");
        }

    }

}
