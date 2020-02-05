package de.th_rosenheim.ad.lecture05;


import java.util.LinkedList;

public class HashtableChaining<Key, Value> {
	private static final int INIT_CAPACITY = 4;

	private int n;                                // number of key-value pairs
	private int m;                                // hash table size

	// each node stores a key and a value and represents an entry of a linked list
	private class Node {
		private Key key;
		private Value val;
		private Node next;
	}

	// the actual hash table
	private Object[] heads;  // each element of this array keeps a pointer to the first element of a linked list
							 // (generic array creation not allowed, therefore Object is used)

	// initializes an empty table
	public HashtableChaining() {
		this(INIT_CAPACITY);
	}


	// initializes an empty table with m chains
	public HashtableChaining(int m) {
		this.m = m;
		heads = new Object[m];

	}

	// resize the hash table to have the given number of chains, rehashing all of the keys
	private void resize(int chains) {
		HashtableChaining<Key, Value> temp = new HashtableChaining<Key, Value>(chains);
		for (int i = 0; i < m; i++) {   // iterate over all bins of hashtable
			Node current = (Node) heads[i];
			while (current != null) {
				temp.put(current.key, current.val);
				current = current.next;
			}
		}
		this.m  = temp.m;
		this.n  = temp.n;
	}

	// hash value between 0 and m-1
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	// returns number of key-value pairs in this symbol table
	public int size() {
		return n;
	}


	// returns true if this symbol table is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	// returns true if this symbol table contains the specified key
	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the specified key in this symbol table.
	 *
	 * @param  key the key
	 * @return the value associated with {@code key} in the symbol table;
	 *         {@code null} if no such value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	// returns value associated with the specified key in this symbol table.
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		for (Node x = (Node) heads[i]; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}


	// inserts specified key-value into the hashtable
	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		// double table size if average length of list >= 10
		if (n >= 10*m) resize(2*m);

		int i = hash(key);
		for (Node x = (Node) heads[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}

		// add as new head
		Node newNode = new Node();
		newNode.key = key;
		newNode.val = val;
		newNode.next = (Node) heads[i];
		heads[i] = newNode;
		n++;
	}


	// removes specified key and value from symbol table (if key is already in symbol table)
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		int i = hash(key);
		Node current = (Node) heads[i];
		Node prev = null;
		while (current != null)  {   // search key to be deleted
			if (key.equals(current.key)) {
				if (prev == null) {      // element to be deleted is first element
					heads[i] = current.next;
				}
				else {
					prev.next = current.next;
				}
				n--;
				// halve table size if average length of list <= 2
				if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
				return;
			}
			prev = current;
			current = current.next;
		}
		return;
	}


	// return keys in symbol table as an Iterable
	public Iterable<Key> keys() {
		LinkedList<Key> list = new LinkedList<Key>();
		for (int i = 0; i < m; i++) {
			for (Node x = (Node) heads[i]; x != null; x = x.next) {
				list.add(x.key);
			}
		}
		return list;
	}


    // test the implementation
	public static void main(String[] args) {
		HashtableChaining<String, Integer> st = new HashtableChaining<String, Integer>();
		st.put("Frankreich", 2018);
		st.put("Deutschland", 2014);
		st.put("Spanien", 2010);
		st.put("Italien", 2006);
				// print keys
		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));

	}

}




