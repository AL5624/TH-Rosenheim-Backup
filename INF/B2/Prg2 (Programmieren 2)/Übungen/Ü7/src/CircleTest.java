import org.junit.Test;

import static org.junit.Assert.*;

public class CircleTest {


    Circle N1 = new Circle(2, "green");
    Circle N2 = new Circle(3, "green");

    @Test
    public void SquareTest()
    {
        assertEquals("Kreisradius", 3, N1.getRadius(), 0);
        System.out.println(N1.getLineWidth());
        System.out.println(N2.getLineWidth());
    }

    @Test
    public void getRadiusTest() {
    }

    @Test
    public void getAreaTest() {
        assertEquals("Flaecheninhalt", 12.6, N1.getArea(), 0.04);
    }

    @Test
    public void getPerimeterTest() {
    }

    @Test
    public void toStringTest() {
    }
}