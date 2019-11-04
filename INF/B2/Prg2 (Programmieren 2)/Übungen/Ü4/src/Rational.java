import java.util.Scanner;

/**
 * Created by Wolfgang Mühlbauer on 01.04.2016 based on previous versions from Prof. Breunig, Hüttl, Deubler, ...
 */

public final class Rational {

    // constants
    public static final String DELIMITER = "/";       // "Bruchstrich"
    private static final long PRECISION = 10000000L;  // definiert Anzahl Nachkommastellen bei "double"

    private long numerator;     // zaehler
    private long denominator;   // nenner



    /**
     * Ermittlung des größten gemeinsamen Teilers (GCD = greatest common devisor)
     *
     * @param a (positive) ganze Zahl
     * @param b (positive) ganze Zahl
     * @return größter gemeinsamer Teiler
     */
    private static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b)
            return gcd(b, a);
        if (b == 0)
            return a; // Null wird von jeder Zahl geteilt
        long c = 1;
        while (c != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }


    /**
     * Kürzt den Bruch und stellt sicher, dass Nenner positiv ist.
     * Attribut numerator und denominator darf modifiziert werden.
     */
    private void norm() {

        // kürzen

        long gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0) {  // Nenner positiv machen
            numerator = -numerator;
            denominator = -denominator;
        }

        // TODO

        // Nenner positiv machen



        // TODO

    }


    // Konstruktoren

    /**
     * Erzeugt Bruch mit Wert 0, d.h. 0/1
     */
    public Rational() {

        // TODO
        numerator = 0;
        denominator = 1;
    }

    /**
     * Erzeugt Bruch "num"/"den" mit Zähler und Nenner vom Typ long
     *
     * @param num Zähler
     * @param den Nenner
     */
    public Rational(long num, long den) {

        // TODO

        numerator = num;
        denominator = den;
        norm();
    }

    /**
     * Erzeugt ganzzahligen Bruch "val"/1. (=Bruchdarstellung von ganzen Zahlen)
     *
     * @param val long
     */
    public Rational(long val) {

        this(val, 1);

    }

    /**
     * Erzeugt Bruch aus double
     *
     * @param val double-Wert
     */
    public Rational(double val) {
        // Mit "this" lässt sich (anderer) Konstruktor aufrufen!!!

        this((long) (val * PRECISION), PRECISION);

    }

    /**
     * Erzeugt Bruch aus String der Form "x/y"
     *
     * @param val String
     */
    public Rational(String val) {

        // TODO




    }

    // get-Methoden

    /**
     * gibt den privaten Zähler
     *
     * @return Zähler
     */
    public long getNumerator() {

        // TODO
        return numerator;
    }

    /**
     * gibt den privaten Nenner
     *
     * @return Nenner
     */
    public long getDenominator()
    {
        // TODO
        return denominator;
    }

    // einfache Umwandlungen

    /**
     * Gibt die Rationalzahl als double zurück indem der Zähler durch den Nenner dividiert wird.
     *
     * @return die Rationalzahl als Double
     */
    public double doubleValue() {

        // TODO
        double i = ((double) numerator) / denominator;

        return i;
    }

    /**
     * Ermittlung der Stringdarstellung
     *
     * @return eine Stringdarstellung der Rationalzahl der Form "a/b"
     */
    public String toString() {

        // TODO
        String i = numerator + "/" + denominator;
        return i;
    }

    /**
     * Negation = Multiplikation mit -1
     *
     * @return den negativen Bruch
     */
    public Rational negate() {

        // TODO
        // Achtung: neues Objekt zurückgeben
        Rational Bruch = new Rational(this.numerator * -1, this.denominator);

        return Bruch;
    }

    /**
     * Kehrbruch
     *
     * @return den Kehrbruch (aus a/b wird b/a)
     */
    public Rational invert() {

        // TODO
        // Achtung: neues Objekt zurückgeben

        Rational Bruch = new Rational(this.denominator, this.numerator);

        return Bruch;
    }

    // Bruchrechnen

    /**
     * Zwei Brüche (a,b) werden addiert. Dazu wird der Bruch erweitert
     *
     * @param val der Bruch (b), welcher addiert werden soll.
     * @return Eine neue Rationalzahl als Ergebnis der Operation
     */
    public Rational add(Rational val) {

        // TODO
        // Achtung: neues Objekt zurückgeben

        long num = this.numerator * val.denominator + val.numerator * this.denominator;
        long denom = this.denominator * val.denominator;
        Rational Bruch = new Rational(num, denom);

        return Bruch;

    }

    /**
     * Zieht von einem Bruch einen anderen ab (a-b) indem mit dem negierten Bruch
     * addiert wird (a+(-b)). Dieses Methode ist ein Beispiel für gute, wenig redundante Programmierung,
     * indem die substract-Methode auf eine bestehende Methode abgebildet wird, ohne die Logik
     * erneut zu programmieren.
     *
     * @param val der Bruch (b), der abgezogen wird
     * @return Eine neue Rationalzahl als Ergebnis der Operation
     */
    public Rational subtract(Rational val) {

        // TODO

        Rational Bruch = this.add(val.negate());

        return Bruch;

    }

    /**
     * Multipliziert zwei Brüche (a,b)
     *
     * @param val der Bruch (b)
     * @return Eine neue Rationalzahl als Ergebnis der Operation
     */
    public Rational multiply(Rational val) {

        // TODO
        // Achtung: neues Objekt zurückgeben


        long num = this.numerator * val.numerator;
        long denom = this.denominator * val.denominator;
        Rational Bruch = new Rational(num, denom);

        return Bruch;
    }

    /**
     * Teilt einen Bruch durch einen anderen, indem mit dem Kehrbruch multipliziert wird.
     *
     * @param val der Bruch durch den geteilt wird.
     * @return Eine neue Rationalzahl als Ergebnis der Operation
     */
    public Rational divide(Rational val) {

        // TODO

        long num = this.numerator * val.denominator;
        long denom = this.denominator * val.numerator;
        Rational Bruch = new Rational(num, denom);

        return Bruch;

    }

}

