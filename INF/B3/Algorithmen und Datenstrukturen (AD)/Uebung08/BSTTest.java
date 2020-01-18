import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;



// created by Wolfgang Mühlbauer
public class BSTTest {

    private BST<Integer, String> bst;

    @Before
    public void setUp() {
        // create binary search tree
        bst = new BST<Integer, String>();
        // create nodes
        bst.put(7, "A");
        bst.put(6, "B");
        bst.put(10, "C");
        bst.put(13, "D");
        bst.put(12, "E");
        bst.put(16, "F");
        bst.put(23, "G");
        bst.put(18, "H");
        bst.put(20, "I");
        bst.put(15, "J");
        bst.put(5, "K");
        bst.put(3, "L");
        String result = bst.toString();
        System.out.println("\nInitial tree (Level-Order): " + result);

    }



    @Test
    public void testPreOrder() {
        StringBuilder result = new StringBuilder();
        for (Integer i : bst.keysPreOrderIterative()) {
            result.append(i + " ");
        }
        String s = result.toString().trim();
        assertEquals("7 6 5 3 10 13 12 16 15 23 18 20", s);
    }
}