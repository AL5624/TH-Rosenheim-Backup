package de.th_rosenheim.ad.uebung09;


import java.util.Stack;

public class BTree<Key extends Comparable<Key>, Value> {

    private static final int T = 2;       // order t of tree, see lecture slides

    private BNode root;                   // root of BTree

    private class BNode<Key extends Comparable<Key>, Value> {
        private int n;                                           // number of keys currently stored in node
        private Comparable[] keys = new Comparable[2 * T - 1];   // sorted array of key values
        private Object[] vals = new Object[2 * T - 1];           // value[i] belongs to key[i]
        BNode children[] = new BNode[2 * T];                     // pointers to children
        boolean leaf = true;                                     // leaf or inner node
    }


    // constructor
    public BTree() {
        root = new BNode();
    }



    // search key in BTree, starting at root
    public BNode get(Key key) {
        return get(root, key);
    }


    // search for a key in subtree rooted at node x
    public BNode get(BNode x, Key key) {
        int i = 0;
        // TODO
        while (i < x.n && key.compareTo((Key) x.keys[i]) > 0 ) {    // iterate over key array
            i++;
        }

        if (i < x.n && x.keys[i].equals(key)) {        // found
            return x;
        }
        else if (x.leaf == true) {                    // not found
            return null;
        }
        else {                                        // else: go down to child
            return get(x.children[i], key);
        }
    }

    //  split child node of node x that has index i ("children[i]" -> y)
    public void splitChild(BNode x, int i) {
        BNode y = x.children[i];                       // node to be split
        BNode z = new BNode();                         // create new node z, taking the larger keys of y, smaller keys remain in y
        z.leaf = y.leaf;
        z.n = T - 1;

        // copy end of y into front of z
        for (int j = 0; j < T - 1; j++) {
            z.keys[j] = y.keys[T + j];
            x.vals[j] = y.vals[T + j];
        }
        if (!y.leaf) {                                 //if not leaf we have to reassign children nodes.
            for (int j = 0; j < T; j++) {
                z.children[j] = y.children[j + T];
            }
        }
        y.n = T - 1;                                   //new size of y

        // adjust children pointers in parent node x, "i + 1" points to new node
        for (int j = x.n; j > i; j--) {
            x.children[j + 1] = x.children[j];
        }
        x.children[i + 1] = z;

        // adjust keys in parent node x, add one new key to parent
        for (int j = x.n - 1; j >= i; j--) {
            x.keys[j + 1] = x.keys[j];
            x.vals[j + 1] = x.vals[j];
        }
        x.keys[i] = y.keys[T - 1];
        x.vals[i] = y.vals[T - 1];

        // delete value in y that went up to parent node
        y.keys[T - 1] = null;
        y.vals[i] = null;

        for (int j = 0; j < T - 1; j++) {    // delete key values / right half of y
            y.keys[j + T] = null;
            y.vals[j + T] = null;
            y.children[j + T] = null;
        }
        y.children[2*T - 1] = null;
        x.n++;
    }


    // insert key into node x that is not full
    public void nonFullInsert(BNode x, Key key, Value val) {
        int i = x.n - 1;                            //i is number of keys in node x
        if (x.leaf) {
            while (i >= 0 && key.compareTo((Key) x.keys[i]) < 0) {      // shift values to make room
                x.keys[i + 1] = x.keys[i];
                x.vals[i + 1] = x.vals[i];
                i--;
            }
            x.keys[i + 1] = key;
            x.vals[i + 1] = val;
            x.n++;
        } else {
            int j = 0;
            while (j < x.n && key.compareTo((Key) x.keys[j]) > 0) {     // find correct child j for recursion
                j++;
            }

            if (x.children[j].n == T * 2 - 1) {
                splitChild(x, j);                    //call splitChild on node x's ith children
                if (key.compareTo((Key) x.keys[j]) > 0) {
                    j++;
                }
            }
            nonFullInsert(x.children[j], key, val);       //recursion to j.th child
        }
    }

    // inserts Key key into BTree
    public void put(Key key, Value val) {
        BNode r = root;
        if (r.n == 2 * BTree.T - 1) {            // if current node r is full
            BNode s = new BNode();  // create new node
            root = s;
            s.leaf = false;
            s.n = 0;
            s.children[0] = r;
            splitChild(s, 0);                  //splitChild root
            nonFullInsert(s, key, val);           //call insert method on new root
        } else {
            nonFullInsert(r, key, val);           //if its not full just insert it
        }
    }

    /**
     * find maximum key stored in subtree rooted at a given node
     *
     * @param x subtree rooted at node x
     * @return maximum key
     */
     public Key maximum(BNode x) {
        while (!x.leaf) {
            x = x.children[x.n];
        }
        return (Key) x.keys[x.n - 1];
    }

    /* rekursive Variante
    public Key maximum(BNode x) {
        if (x.leaf == true) {
            return (Key) x.keys[x.n- 1];
        }
        else {
            return (Key) maximum(x.children[x.n]);
        }
    }
    */


    /**
     * finds the predecessor of a key
     *
     * @param key key for which we search the predecessor
     * @return predecessor key
     */
    public Key predecessor(Key key) {

        // first search the node that should store the key, remember all visited nodes on the search path
        Stack<BNode> searchPath = new Stack<BNode>();
        BNode current = root;
        int i;

        while (current != null && !current.leaf) {
            searchPath.push(current);

            // check if key is stored in this node. If yes don't go on until leaf node is reached
            i = 0;               // i takes the index of the key for which we search the predecessor
            while (i < current.n && key.compareTo((Key) current.keys[i]) > 0) {
                if (current.keys[i] == key) {
                    break;
                }
                i++;
            }
            current = current.children[i];
        }
        // No we are at the node that must contain the key

        // determine index of key in this node
        i = 0;
        while (i < current.n && key.compareTo((Key) current.keys[i]) > 0) {
            i++;
        }
        if (i >= current.n || current.keys[i] != key) {
            throw new RuntimeException("Key for which you search a predecessor is not contained in BTree");
        }

        /*
        do   {    // search down to leaf
            searchPath.push(current);
            i = 0;
            while (i < current.n && key.compareTo((Key) current.keys[i]) > 0) {   // search through key list of current node
                i++;
            }
            if ( i < current.n && key.equals(current.keys[i])) {     // current node contains this key, stop going down to leaf
                break;
            }
            else {          // continue search to child
                current = current.children[i];
                //searchPath.push(current);
            }
        }  while (!current.leaf);

        // if arrived at leaf, final check if leaf contains the tree
        if (current.leaf) {
            i = 0;
            while (i < current.n && key.compareTo((Key) current.keys[i]) > 0) {
                i++;
            }
            if ( i >= current.n || !key.equals(current.keys[i])) {     // key not contained in tree, we don't have to search predecessor
                throw new RuntimeException("Key for which you search a predecessor is not contained in BTree");
            }
        }
        */


        // NOW WE SEARCH THE ACTUAL PREDECESSOR (knowing that they key for which we search is in node "current" at index i)

        // if not leaf, return maximum in subtree rooted at children[i]
        if (!current.leaf) {
            return (Key) maximum(current.children[i]);
        }

        // node x is a leaf and therefore it directly stores the predecessor
        // Only exception: the key for which we search the predecessor is the leftmost key
        else if (current.leaf && i >= 1) {
            return (Key) current.keys[i - 1];
        }

        // case where node x is a leaf and key for which we search the predecessor is the leftmost key in node x
        else {
            // walk up tree towards root, going along search path in reverse order;
            BNode temp = current;  ;            // stores currently visited node on the way back along the search path
            BNode prev;                         // node from where we are coming
            while (!searchPath.isEmpty())  {

                prev = temp;
                temp = searchPath.pop();

                // check from which child we are coming (it's called jth child in the following)
                int j = 0;
                while (j <= temp.n && temp.children[j] != prev) {
                    j++;
                }

                // predecessor must be key[j-1] if we come from children[j]
                if (j > 0) {
                    return (Key) temp.keys[j - 1];
                }
            }
            // if we reach this code, there is no predecessor, since we have searched for the predecessor of the minimum key
            return null;
        }

    }






}









































