package de.th_rosenheim.ad.uebung12;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyPriorityQueueTest {

    MyPriorityQueue<Integer> pq;

    @Before
    public void setUp() {
        int[] integers = { 8, 9, 30, 15, 20, 50, 60, 16, 23, 29, 22};
        pq = new MyPriorityQueue<Integer>(integers.length);
        // ith element of array "integer" receives handle i
        for (int i = 0; i < integers.length; i++) {
            pq.insert(i, integers[i]);
        }
    }

    // test: delete index 1 (== 9)
    @Test
    public void delete9() {
        pq.delete(1);
        assertFalse(pq.contains(1));                    // check that index 1(==9) now contained any longer
        assertTrue(pq.getHeapPos(3) == 1);   // check that index 3(==15) is now at heap psoition 1
        assertTrue(pq.getHeapPos(10) == 7);  // check that index 10(==22)  is now at heap position 3
    }

    // test: delete index 6 (== 60)
    @Test
    public void delete60() {
        pq.delete(6);
        assertFalse(pq.contains(6));                     // check that index 6(== 60) not contained any longer
        assertTrue(pq.getHeapPos(10) == 2);   // check that index 10(== 22) is now at heap position 2
        assertTrue(pq.getHeapPos(2) == 6);    // check that index 2(== 30) is now at heap position 6
    }

    // test: increase index 1 (==9) to 100
    @Test
    public void changeKey9To100() {
        pq.changeKey(1, 100);
        assertTrue(pq.contains(1));                       // check that index 1(==9) is still contained
        assertTrue(pq.getHeapPos(1) == 7);    // changed element (index 1, new value 100) now takes heap position 7
        assertTrue(pq.getHeapPos(3) == 1);     // check that index 3(==15) is now at heap position 1
    }

    // test: decrease index  1 (==9) to 7
    @Test
    public void changeKey9To7() {
        pq.changeKey(1, 7);
        assertTrue(pq.contains(9));
        assertTrue(pq.getHeapPos(0) == 1);    // check that index 1 (==9, new value 7) is now at heap position 0
    }

}