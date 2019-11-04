import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {

    Square N1 = new Square(3);


    @Test
    public void getSideTest() {

        assertEquals("Seitenlänge", 3, N1.getSide(), 0);
    }

    @Test
    public void setSideTest() {
        N1.setSide(4);
        assertEquals("Neue Seite", 4, N1.getSide(), 0);
    }
}