package de.th_rosenheim.ad.uebung03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingTest {

    // check that 1,2,3,4 are in the correct order
    @Test
    void enqueue() {
        Ring<Integer> r = new Ring<Integer>();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        assertEquals("1 2 3 4", r.toString());
        assertEquals(4, r.size());
    }

    // when queue is empty, removed element should be null
    @Test
    void dequeueNoElementinQueue() {
        Ring<Integer> r = new Ring<Integer>();
        Integer removed = r.dequeue();
        assertNull(removed);
        assertEquals(0, r.size());
    }

    // when queue has a single element, this should be the removed element
    @Test
    void dequeueSingleElementinQueue() {
        Ring<Integer> r = new Ring<Integer>();
        Integer i = new Integer(4);
        r.enqueue(i);
        Integer removed = r.dequeue();
        assertSame(removed, i);
        assertEquals(0, r.size());
    }

    // when queue has >1 element, check that removed element is the one that was added first
    @Test
    void dequeue() {
        Ring<Integer> r = new Ring<Integer>();
        Integer i = new Integer(4);
        r.enqueue(i);
        r.enqueue(3);
        r.enqueue(2);
        Integer removed = r.dequeue();
        assertSame(removed, i);
        assertEquals(2, r.size());
    }


}


