package de.th_rosenheim.ad.lecture03;

import java.util.NoSuchElementException;

public class MyArrayQueue<Item> {
    private Item[] items;   // queue elements
    private int n;          // number of elements on queue
    private int head;       // index of head element of queue
    private int tail;       // index of next available slot

    // create an empty with 10 elements, on demand resizing if needed
    public MyArrayQueue() {
        items = (Item[]) new Object[10];
        n = 0;
        head = 0;
        tail = 0;
    }


    // Is this queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return number of items in queue
    public int size() {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[(head + i) % items.length];
        }
        items = temp;
        head = 0;
        tail = n;
    }

    // adds item to this queue
    public void enqueue(Item item) {
        // double size of array if necessary and recopy to front of array
        if (n == items.length) {
            resize(2 * items.length);   // double size of array if necessary
        }
        items[tail++] = item;
        if (tail == items.length)
            tail = 0;
        n++;
    }

    // removes and returns the item on this queue that was least recently added
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = items[head];
        items[head++] = null;                            // to avoid loitering
        if (head == items.length)
            head = 0;
        n--;
        return item;
    }


}
