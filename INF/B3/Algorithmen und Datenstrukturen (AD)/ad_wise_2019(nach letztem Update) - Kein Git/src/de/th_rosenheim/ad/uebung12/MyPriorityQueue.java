package de.th_rosenheim.ad.uebung12;


import java.util.List;
import java.util.NoSuchElementException;


// taken from Sedgewick et al, adjusted by W. Mühlbauer
public class MyPriorityQueue<Key extends Comparable<Key>>  {
    private int maxN;           // maximum number of elements on PQ
    private int n;              // number of elements on PQ

    private Key[] keys;         // keys[i] = priority of i
    private int[] pq;           // BINARY HEAP ORDER acc. to natural order of keys (value pq[i] is an index, pointing to a key)
    private int[] heapPos;      // the inverse to pq: heapPos[i] tells the position of i in the current binary heap


    // initializes an empty indexed priority queue with indices between 0 and maxN - 1
    public MyPriorityQueue(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        pq = new int[maxN];
        heapPos = new int[maxN];
        keys = (Key[]) new Comparable[maxN];

        for (int i = 0; i < maxN; i++)
            heapPos[i] = -1;
    }



    // returns true if this priority queue is empty
    public boolean isEmpty() {
        return n == 0;
    }

    // Is i an index on this priority queue
    public boolean contains(int i) {
        return heapPos[i] != -1;
    }

    // returns number of keys in this priority queue
    public int size() {
        return n;
    }



    // insert key (with index i) into priority queue, index is needed for quick access to key later.
    public void insert(int i, Key key) {
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        pq[n - 1] = i;                  // add at the end of binary heap
        heapPos[i] = n - 1;
        keys[i] = key;

        int j = n - 1;
        swim(j);
    }


    // returns minimum key and returns its associated index
    public int extractMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[0];
        exchange(0, n--);    // put last element at the top of the binary heap
        minHeapify(0);

        keys[min] = null;       // to help with garbage collection
        heapPos[min] = -1;
        pq[n] = -1;             // invalidate (actually not needed, to be safe)
        return min;
    }


    // only for testing!!! returns
    public int getHeapPos(int i) {
        return heapPos[i];
    }

    // returns key associated with index i
    public Key keyOf(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    // decrease key associated with index i to the specified value
    public void decreaseKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
        if (keys[i].compareTo(key) < 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
        keys[i] = key;
        swim(i);
    }

    // delete element with index i
    public void delete(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int pos = heapPos[i];
        exchange(pos, --n);
        swim(pos);
        minHeapify(pos);
        keys[i] = null;
        heapPos[i] = -1;
    }

    // change value of element with index i
    public void changeKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(heapPos[i]);
        minHeapify(heapPos[i]);
    }


    // swaps elements in binary heap
    private void exchange(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        heapPos[pq[i]] = i;
        heapPos[pq[j]] = j;
    }

    // returns index of parent node (array starts with index 0)
    private final int parent(int i) {
        return (i + 1) / 2 - 1;
    }


    // returns index of left child (array starts with index 0)
    private final int left(int i) {
        return 2 * i + 1;
    }

    // returns index of right child (array starts with index 0)
    private final int right(int i) {
        return 2 * i + 2;
    }


    // repair heap: element "i" may be larger than its children -> "versickern"
    private void minHeapify(int i) {
        int min = i;

        if (left(i) < n && keys[pq[left(i)]].compareTo(keys[pq[min]]) < 0) {
            min = left(i);
        }
        if (right(i) < n && keys[pq[right(i)]].compareTo(keys[pq[min]]) < 0) {
            min = right(i);
        }
        if (min != i) {
            exchange(min, i);
            minHeapify(min);
        }
    }

    // repair heap: element "i" may be smaller than elements up in the tree -> "hochspülen"
    private void swim(int i) {
        while (i > 0 && keys[pq[parent(i)]].compareTo(keys[pq[i]]) > 0) {
            exchange(i, parent(i));
            i = parent(i);
        }
    }
}