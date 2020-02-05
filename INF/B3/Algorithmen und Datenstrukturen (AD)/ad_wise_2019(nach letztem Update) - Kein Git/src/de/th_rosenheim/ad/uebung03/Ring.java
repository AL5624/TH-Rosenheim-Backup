package de.th_rosenheim.ad.uebung03;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Ring<Item> {

    // inner class: groups data value and prev/next "pointer"
	private class Node{
        Item item;
        Node prev;   // pointer to next node
        Node next;   // pointer to previous node
    }

	private Node entry;  // entry point into ring
	private int n; 		 // number of items in ring

	// constructure: empty ring
	public Ring() {
		entry = null;
		n = 0;
	}

	// check if ring is empty
	public boolean isEmpty() {
		return entry == null;
	}

	// return number of items in queue
	public int size() {
		return n;
	}

	/* enqueue, adding element into queue IMPORTANT:
	    - add new element after current "entry" element.
	    - "entry" points afterwards to new node (== tail of queue)
	 */
	public void enqueue(Item item) {
		Node newNode = new Node();
		newNode.item = item;
		if (entry == null) {  			 // ring was empty so far
			newNode.prev = newNode;
			newNode.next = newNode;
		} else {
			entry.next.prev = newNode;
			newNode.next = entry.next;
			newNode.prev = entry;
			entry.next = newNode;
		}
		entry = newNode;
		n++;
	}

	// dequeue, remove element from queue.
	public Item dequeue() {
		if (entry == null) {    	  // queue was empty
 			return null;
		}
		else if (entry.next == entry)  {   // last element to be deleted
			Node toDelete = entry;
			entry = null;
			n--;
			return toDelete.item;
		}
		else {     					 // there are more than one node
			Node toDelete = entry.next;
			entry.next.next.prev = entry;
			entry.next = entry.next.next;
			n--;
			return toDelete.item;
		}
	}

	public String toString() {
		if (n == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		Node current = entry.next;
		for (int i = 0; i < n; i++) {
			result.append(current.item.toString());
			result.append(" ");
			current = current.next;
		}
		return result.toString().trim();
	}
}
