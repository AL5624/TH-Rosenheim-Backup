import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BTreeTest {

    BTree<Integer, String> btree;


    @Before
    public void setUp() {
        btree = new BTree<Integer, String>();
        btree.put(1, "a");
        btree.put(6, "b");
        btree.put(10, "c");
        btree.put(14, "d");
        btree.put(18, "e");
        btree.put(2, "f");
        btree.put(17, "g");
        btree.put(5, "h");
        btree.put(4, "i");
        btree.put(9, "j");
        btree.put(20, "k");
        btree.put(25, "l");
        btree.put(22, "m");
        btree.put(21, "n");
        btree.put(26, "o");
        btree.put(27, "p");
        btree.put(28, "q");
        btree.put(29, "r");
        btree.put(30, "s");
        btree.put(31, "t");
    }

    @Test
    public void maximum()  {
        assertEquals(31, btree.maximum(btree.get(18)), 31);
    }

    @Test
    // predecessor of 18
    public void predecessorNonLeaf() {
        assertTrue(btree.predecessor(18).compareTo(17) == 0);
    }

    @Test
    // predecessor of 30
    public void predecessorLeafNotLeftMostIndex() {
        assertTrue(btree.predecessor(30).compareTo(29) == 0);
    }

    @Test
    //predecessor of 29
    public void predecessorLeafLeftMostIndex() {
        assertTrue(btree.predecessor(20).compareTo(18) == 0);
    }

    @Test
    //predecessor of 1
    public void predecessorNotExists() {
        assertTrue(btree.predecessor(1) == null);
    }

}