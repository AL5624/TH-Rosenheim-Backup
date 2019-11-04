package tum.it11a.a.wust.datenstrukturen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bauer on 21.12.2016.
 */
public class DoubleLinkListTest {
    DoubleLinkList list;

    @Before
    public void setUp() throws Exception {
        this.list = new DoubleLinkList();
        DoubleLinkList temp = this.list;
        temp = new DoubleLinkList(3.0, null);
        temp = new DoubleLinkList(2.0, temp);
        temp = new DoubleLinkList(1.0, temp);
        temp = new DoubleLinkList(0.0, temp);
        temp = new DoubleLinkList(-1.0, temp);
        temp = new DoubleLinkList(-2.0, temp);
        temp = new DoubleLinkList(null, temp);
        this.list = temp;
    }

    @After
    public void tearDown() throws Exception {
        this.list = null;
    }

    @Test
    public void testToString() throws Exception {
        DoubleLinkList tempList = new DoubleLinkList();
        String actual;
        String expected;

        //leere Liste
        actual = tempList.toString();
        expected = "[]";
        assertEquals(expected,actual);

        //kurze Liste
        tempList = new DoubleLinkList(null, new DoubleLinkList(8.0));
        actual = tempList.toString();
        expected = "[8.0]";
        assertEquals(expected,actual);

        //längere Liste
        tempList = this.list;
        actual = tempList.toString();
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
        list.remove(5);
        assertEquals("[-1.0, 1.0, 2.0]", list.toString());
    }

    @Test
    public void testRemoveValue() throws Exception {
        list.removeValue(0.0);
        assertEquals("[-2.0, -1.0, 1.0, 2.0, 3.0]", list.toString());
        list.removeValue(-2.0);
        assertEquals("[-1.0, 1.0, 2.0, 3.0]", list.toString());
        list.removeValue(3.0);
        assertEquals("[-1.0, 1.0, 2.0]", list.toString());
        list.removeValue(3.0);
        assertEquals("[-1.0, 1.0, 2.0]", list.toString());
    }

    @Test
    public void testAddIndex() throws Exception {
        this.list.add(0, 12.0);
        assertEquals("[12.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0]", this.list.toString());
        this.list.add(2, 12.0);
        assertEquals("[12.0, -2.0, 12.0, -1.0, 0.0, 1.0, 2.0, 3.0]", this.list.toString());
        this.list.add(7, 12.0);
        assertEquals("[12.0, -2.0, 12.0, -1.0, 0.0, 1.0, 2.0, 12.0, 3.0]", this.list.toString());
        this.list.add(9, 22.0);
        assertEquals("[12.0, -2.0, 12.0, -1.0, 0.0, 1.0, 2.0, 12.0, 3.0, 22.0]", this.list.toString());
        this.list.add(1000, 44.0);
        assertEquals("[12.0, -2.0, 12.0, -1.0, 0.0, 1.0, 2.0, 12.0, 3.0, 22.0, 44.0]", this.list.toString());
    }

    @Test
    public void testClear() throws Exception {
        list.clear();
        assertEquals("[]", list.toString());
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(-2.0, (double)list.get(0), 0.0);
        assertEquals(-1.0, (double)list.get(1), 0.0);
        assertEquals(0.0, (double)list.get(2), 0.0);
        assertEquals(3.0, (double)list.get(5), 0.0);
        assertEquals(null, list.get(6));
        assertEquals(null, list.get(1000));
    }

    @Test
    public void testSet() throws Exception {
        list.set(0, 99.0);
        assertEquals("[99.0, -1.0, 0.0, 1.0, 2.0, 3.0]", list.toString());
        list.set(2, 99.0);
        assertEquals("[99.0, -1.0, 99.0, 1.0, 2.0, 3.0]", list.toString());
        list.set(5, 99.0);
        assertEquals("[99.0, -1.0, 99.0, 1.0, 2.0, 99.0]", list.toString());
        list.set(1000, 99.0);
        assertEquals("[99.0, -1.0, 99.0, 1.0, 2.0, 99.0]", list.toString());
    }

    @Test
    public void testContains() throws Exception {
        assertTrue(list.contains(-2.0));
        assertTrue(list.contains(-1.0));
        assertTrue(list.contains(2.0));
        assertTrue(list.contains(3.0));

        assertFalse(list.contains(99.0));
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(6, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testSort() throws Exception {
//        Arrays.deepEquals(new Integer[]{4,5}, list.sort());
    }
}