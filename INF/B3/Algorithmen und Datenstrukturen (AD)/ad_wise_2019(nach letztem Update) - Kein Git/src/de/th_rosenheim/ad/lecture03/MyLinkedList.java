package de.th_rosenheim.ad.lecture03;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinkedList<Item> implements Iterable<Item> {

    // inner class
    private class Node<Item> {
        Item item;
        Node next;  // next node
        Node prev;  // previous node
    }

    private Node head;  // first node
    private Node tail;  // last node
    private int n; 		// current number of elements

    // add element to the end
    public void add(Item x)  {
        Node temp = new Node();
        temp.prev = tail;
        temp.next = null;
        temp.item = x;

        if (n == 0)     // list is empty, set head to new element
            head = temp;
        else
            tail.next = temp;

        tail = temp;
        n++;

    }

    // get position of element
    public int indexOf(Item x) {
       Node temp = head;
       int i = 0;

       return -1;
    }

    // remove element x
    public void remove(Item x) {
         Node cur = head;
        // search element
        while (cur != null && !cur.item.equals(x)) {
            cur = cur.next;
        }

        if (cur == null) {    // list was empty or not found
            return;
        }

        if (cur.prev == null) {   // there was only 1 element: set head
            head = null;
        }
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;

        n--;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n > 0 ? true : false;
    }



    // returns an iterator that iterates over the items
    public Iterator<Item> iterator()  {
        return new ListIterator(head);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }

        // doesn't implement remove() since it's optional
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
