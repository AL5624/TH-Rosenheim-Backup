package de.th_rosenheim.ad.lecture03;


import java.util.ArrayList;

public class MyArrayList<Item> {
	
	private Item[] items;        // array to store items
	private int n; 		       	 // current number of elements

	public MyArrayList(int capacity) {
		items = (Item []) new Object[capacity];
		n = 0;
	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			temp[i] = items[i];
		items = temp;
	}

	// add element to arrayList
	public void add(Item x) throws Exception {
		if (n == items.length) {
			resize(2*items.length);
		}
		items[n] = x;
		n++;
	}


	// get position of element
	public int indexOf(Item x) {
		for (int i=0; i<n; i++) {
			if (items[i].equals(x)) {
				return i;
			}
		}
		return -1;
	}


	// remove element with index i
	public Item remove(int index) {
		if (index >= n || index < 0) {
			return null;
		}
		Item temp = items[index];
		for (int i = index + 1; i < n; i++) {
			items[i-1] = items[i];
		}
		n--;
		return temp;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n > 0 ? true : false;
	}

}

