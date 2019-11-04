
package tum.it11a.a.wust.datenstrukturen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bauer on 21.12.2016.
 */
public class IndexListTest {
    IndexList<Integer> list;

    @Before
    public void setUp() throws Exception {
        this.list = new IndexList<>();
        this.list.setData(-2, -1, 0, 1, 2, 3);
    }

    @After
    public void tearDown() throws Exception {
        this.list = null;
    }

    @Test
    public void testToString() throws Exception {
        IndexList<Double> list = new IndexList<>();
        String actual;
        String expected;

        //leere Liste
        actual = list.toString();
        expected = "[]";
        assertEquals(expected,actual);

        //kurze Liste
        list.setData(8.0);
        actual = list.toString();
        expected = "[8.0]";
        assertEquals(expected,actual);

        //längere Liste
        list.setData(-2.0, -1.0, 0.0, 1.0, 2.0, 3.0);
        actual = list.toString();
        expected = "[-2.0, -1.0, 0.0, 1.0, 2.0, 3.0]";
        assertEquals(expected,actual);
    }

    @Test
    public void testAdd() throws Exception {
        this.list.add(12);
        String actual = this.list.toString();
        String expected = "[-2, -1, 0, 1, 2, 3, 12]";
        assertEquals(expected,actual);
    }

    @Test
    public void testAddFirst() throws Exception {
        list.addFirst(9);
        String actual = list.toString();
        String expected = "[9, -2, -1, 0, 1, 2, 3]";
        assertEquals(expected,actual);
    }

    @Test
    public void testAddLast() throws Exception {
        list.addLast(9);
        String actual = list.toString();
        String expected = "[-2, -1, 0, 1, 2, 3, 9]";
        assertEquals(expected,actual);
    }

    @Test
    public void testRemove() throws Exception {
        //remove mit Index
        list.remove(0);
        assertEquals("[-1, 0, 1, 2, 3]", list.toString());
        list.remove(4);
        assertEquals("[-1, 0, 1, 2]", list.toString());
        list.remove(1);
        assertEquals("[-1, 1, 2]", list.toString());

        //remove mit Wert

    }
}