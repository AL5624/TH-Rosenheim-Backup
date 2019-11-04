import org.junit.Test;

import static org.junit.Assert.*;

public class RationalTest {

    Rational Bruch1 = new Rational(1,2);
    Rational Bruch2 = new Rational(2,3);
    Rational Bruch3 = new Rational (5, -15);

    @org.junit.Test
    public void invert() {

        Rational Expected = new Rational(2,1);
        Rational Actual = Bruch1.invert();

        assertEquals("Zaehler", Expected.getNumerator(),Actual.getNumerator());
        assertEquals("Nenner", Expected.getDenominator(), Actual.getDenominator());

    }

    @Test
    public void add(){

        Rational Expected = new Rational(7, 6);
        Rational Actual = Bruch1.add(Bruch2);

        assertEquals("Zaehler", Expected.getNumerator(),Actual.getNumerator());
        assertEquals("Nenner", Expected.getDenominator(), Actual.getDenominator());

    }

    @Test
    public void Rational(){
    Rational Expected = new Rational(1, -3);
    Rational Actual = Bruch3;

    assertEquals("Zaehler", Expected.getNumerator(),Actual.getNumerator());
    assertEquals("Nenner", Expected.getDenominator(), Actual.getDenominator());

    }

    @Test
    public void sub(){
        Rational Expected = new Rational (-1, 6);
        Rational Actual = Bruch1.subtract(Bruch2);

        assertEquals("Zaehler", Expected.getNumerator(),Actual.getNumerator());
        assertEquals("Nenner", Expected.getDenominator(), Actual.getDenominator());

    }

    @Test
    public void negate(){

        Rational Expected = new Rational(-2, 3);
        Rational Actual = Bruch2.negate();

        assertEquals("Zaehler", Expected.getNumerator(),Actual.getNumerator());
        assertEquals("Nenner", Expected.getDenominator(), Actual.getDenominator());
    }

    @Test
    public void add2(){

        Rational Expected = new Rational(-1, 6);
        Rational Actual = Bruch1.add(Bruch2.negate());

        assertEquals("Zaehler", Expected.getNumerator(),Actual.getNumerator());
        assertEquals("Nenner", Expected.getDenominator(), Actual.getDenominator());
    }
}