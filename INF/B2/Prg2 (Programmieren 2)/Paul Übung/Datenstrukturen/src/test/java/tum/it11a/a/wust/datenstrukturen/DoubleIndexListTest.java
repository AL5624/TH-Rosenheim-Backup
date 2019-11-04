package tum.it11a.a.wust.datenstrukturen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bauer on 21.12.2016.
 */
public class DoubleIndexListTest {
    IndexList<Double> list;

    @Before
    public void setUp() throws Exception {
        this.list = new IndexList<Double>();
        this.list.setData(-2.0, -1.0, 0.0, 1.0, 2.0, 3.0);
    }

    @After
    public void tearDown() throws Exception {
        this.list = null;
    }

    @Test
    public void testToString() throws Exception {
        DoubleIndexList list = new DoubleIndexList();
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
        this.list.add(12.0);
        String actual = this.list.toString();
        String expected = "[-2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 12.0]";
        assertEquals(expected,actual);
    }

    @Test
    public void testAddFirst() throws Exception {
        list.addFirst(9.0);
        String actual = list.toString();
        String expected = "[9.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0]";
        assertEquals(expected,actual);
    }

    @Test
    public void testAddLast() throws Exception {
        list.addLast(9.0);
        String actual = list.toString();
        String expected = "[-2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 9.0]";
        assertEquals(expected,actual);
    }

    @Test
    public void testRemove() throws Exception {
        //remove mit Index
        list.remove(0);
        assertEquals("[-1.0, 0.0, 1.0, 2.0, 3.0]", list.toString());
        list.remove(4);
        assertEquals("[-1.0, 0.0, 1.0, 2.0]", list.toString());
        list.remove(1);
        assertEquals("[-1.0, 1.0, 2.0]", list.toString());

        //remove mit Wert

    }

    @Test
    public void testAddWithIndex() throws Exception {
        list.add(2, 4.0);
        String actual = list.toString();
        String expected = "[-2.0, -1.0, 4.0, 1.0, 2.0, 3.0]";
        assertEquals(expected,actual);
    }

    @Test
    public void testRemoveValue() throws Exception {
        list.removeValue(0.0);
        String actual = list.toString();
        String expected = "[-2.0, -1.0, 1.0, 2.0, 3.0]";
        assertEquals(expected,actual);
    }

    @Test
    public void testClear() throws Exception {
        list.clear();
        String actual = list.toString();
        String expected = "[]";
        assertEquals(expected,actual);
    }

    @Test
    public void testGet() throws Exception {
        Double result = list.get(486);
        assertNull(result);

        Double actual = list.get(2);
        Double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    public void testSet() throws Exception {
        list.set(2, 4.0);
        String actual = list.toString();
        String expected = "[-2.0, -1.0, 4.0, 1.0, 2.0, 3.0]";
        assertEquals(expected, actual);
    }

    @Test
    public void testContains() throws Exception {
        boolean result = list.contains(1.0);
        assertEquals(true, result);
    }
}