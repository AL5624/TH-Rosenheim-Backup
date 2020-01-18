import java.util.*;

// adapted from Sedgewick et al.
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root
    private int n;                 // number of elements

    private class Node {
        private Key key;                    // key (this is what "sorts" the data
        private Value val;                  // associated data
        private Node left, right, parent;   // pointer to left and right subtrees and the parent

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

     // initializes an empty tree
    public BST() {
    }

    // returns true if no elements are stored in tree
    public boolean isEmpty() {
        return n == 0;
    }

    // returns number of key-value pairs in tree
    public int size() {
        return n;
    }

    // does tree contain the given key (implicitly uses "get")
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // find key in tree, returns value associated with given key    // "recursion wrapper"
    public Value get(Key key) {
        return get(root, key);
    }

    // recursive function to find a key
    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    // insert (key, val) into tree
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {    // inserting  key with value "null" deletes the key
            //delete(key);
            n--;
            return;
        }

        // search position for insertion
        Node prev = null;
        Node current = root;
        while (current != null) {
            prev = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0)      // key must go to left subtree
                current = current.left;
            else if (cmp > 0)
                current = current.right;
            else {           // key already exists, just overwrite value
                current.val = val;
                return;
            }
        }
        Node newNode = new Node(key, val);
        newNode.parent = prev;
        if (n == 0)                             // tree was empty
            root = newNode;
        else if (key.compareTo(prev.key) < 0)   // new node is left child
            prev.left = newNode;
        else                                    // new node is right child
            prev.right = newNode;

        n++;
    }


    // replaces subtree rooted at u by subtree root at v, see lecture
    private void transplant(Node u, Node v) {
        if (u.parent == null) {            // u was the root
            root = v;
        } else if (u == u.parent.left) {    // u was left child
            u.parent.left = v;
        } else {                            // u was right child
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    // deletes key from tree
    public void delete(Key key)
    {

        // TODO

        if(this.contains(key) == false)return;
        Node toDelete = root;
        Node tmp;
        while (!toDelete.key.equals(key))
        {
            if(key.compareTo(toDelete.key) < 0)toDelete = toDelete.left;
            if(key.compareTo(toDelete.key) > 0)toDelete = toDelete.right;
        }

        if(toDelete.left == null && toDelete.right == null)
        {
            if(toDelete != root)
            {
                if (toDelete.parent.left == toDelete) toDelete.parent.left = null;
                else toDelete.parent.right = null;
            }
        }
        else if(toDelete.left == null && toDelete.right != null)
        {
            if(toDelete != root)
            {
                if (toDelete.parent.left == toDelete) toDelete.parent.left = toDelete.right;
                else toDelete.parent.right = toDelete.right;
            }
            else
            {
                root = toDelete.right;
            }
        }
        else if(toDelete.left != null && toDelete.right == null)
        {
            if(toDelete != root)
            {
                if (toDelete.parent.left == toDelete) toDelete.parent.left = toDelete.left;
                else toDelete.parent.right = toDelete.left;
            }
            else
            {
                root = toDelete.left;
            }
        }
        else
        {
            if(toDelete.right.left == null)
            {
                if(toDelete != root)
                {
                    if (toDelete.parent.left == toDelete) toDelete.parent.left = toDelete.right;
                    else toDelete.parent.right = toDelete.right;
                }
                toDelete.right.left = toDelete.left;
            }
            else
            {
                tmp = toDelete.right.left;
                while(tmp.left != null)
                {
                    tmp = tmp.left;
                }
                if(tmp.right != null)tmp.parent.left = tmp.right;
                else tmp.parent.left = null;
                if(toDelete != root)
                {
                    if (toDelete.parent.left == toDelete) toDelete.parent.left = tmp;
                    else toDelete.parent.right = tmp;
                }
                tmp.left = toDelete.left;
                tmp.right = toDelete.right;

            }
        }

        toDelete.right = null;
        toDelete.left = null;
        toDelete.val = null;
        toDelete.key = null;


    }


    // returns smalles key in tree ("recursion wrapper")
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    // returns largest key in tree ("recursion wrapper")
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    // returns the predecessor key (null if it doesn't exist)
    public Key predecessor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = predecessor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node predecessor(Node x, Key key) {
        if (x == null) return null;
        if (x.left != null) {     // case 1: predecessor is maximum of left subtree
            return max(x.left);
        }
        Node y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    // returns the successor key (null if it doesn't exist)
    public Key successor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = successor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node successor(Node x, Key key) {
        if (x == null) return null;
        if (x.right != null) {   // case 1: successor is minimum of right subtree
            return min(x.right);
        }
        Node y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * computes height of the subtree rooted at x, i.e. the longest path from x to any of the leaves.
     * Assumption: We count the number of edges along this path.
     * @param x node for which height of subtree is computed
     * @return
     */
    public int height(Node x)
    {
        // TODO
        int max = -1, tmpHeight = 0;
        if(x.left == null && x.right == null)return 0;
        else
        {
            if(x.left != null)
            {
                tmpHeight = height(x.left);
                if (tmpHeight > max) max = tmpHeight;
            }
            if(x.right != null)
            {
                tmpHeight = height(x.right);
                if (tmpHeight > max) max = tmpHeight;
            }
        }

        return max + 1;
    }

    public int height() {
        return height(root);
    }


    // returns an iterator that iterates over the keys in in-order
    public Iterable<Key> keysInOrderTraversal() {
        List<Key> keys = new LinkedList<>();
        return inOrder(root, keys);
    }
    private List<Key> inOrder(Node n, List<Key> keys) {
        if (n !=null) {
            keys = inOrder(n.left, keys);
            keys.add(n.key);
            keys = inOrder(n.right, keys);
        }
        return keys;
    }


    // get list of keys in level-order
    public Iterable<Key> keysLevelOrderTraversal() {
        List<Key> keys = new LinkedList<>();
        Queue<Node> queue = new LinkedList<Node>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {              // core idea: elements that are seen first, are added to queue first
            Node n = queue.remove();            // FIFO: remove "oldest" element
            keys.add(n.key);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
        return keys;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Key k : this.keysLevelOrderTraversal()) {
            result.append(k.toString() + " ");
        }
        return result.toString().trim();
    }

}





