import java.util.LinkedList;
import java.util.Queue;

public class HashtableProbingLazyDelete<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the hash table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values


    // creates an empty hash table
    public HashtableProbingLazyDelete() {
        this(INIT_CAPACITY);
    }

    // creates an empty hash table with the specified capacity
    public HashtableProbingLazyDelete(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    // returns number of key-value pairs in this hash table
    public int size() {
        return n;
    }

    // returns true is this hash table is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // helper methods for testing
    public int getTableSize() {return m; }                   //  GETTER: return value of m
    public Key getTableEntryAtIndex(int i) {return keys[i];}  // GETTER: return key at table position i



    // returns true if this hash table contains the key
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        HashtableProbingLazyDelete<Key, Value> temp = new HashtableProbingLazyDelete<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }

    // inserts the specified key-value pair into the symbol table
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {    // delete key if value is not specified
            delete(key);
            return;
        }

        // double table size if 50% full
        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {    // key already exists in table, overwrite old value
                vals[i] = val;
                return;
            }else if(vals[i] == null)
            {
                /*keys[i] = key;
                vals[i] = val;
                n++;
                return;*/
                break;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    // return value associated with the specified key
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key)) return vals[i];

        return null;
    }


    public void delete(Key key) {
        // TODO
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        vals[i] = null;
        n--;

        if (n > 0 && n <= m/8) resize(m/2);


    }

    /* eager delete method
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) resize(m/2);
    }
    */



    // return Iterator
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }


}
