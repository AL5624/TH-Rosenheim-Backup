import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

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
    public void testDelete3() {
        bst.delete(3);
        String result = bst.toString();
        System.out.println("\nTree after deleting key 3 (Level-Order): " + result);
        assertEquals("7 6 10 5 13 12 16 15 23 18 20", result);
    }

    @Test
    public void testDelete10() {
        bst.delete(10);
        String result = bst.toString();
        System.out.println("\nTree after deleting key 10 (Level-Order): ");
        assertEquals("7 6 13 5 12 16 3 15 23 18 20", result);
    }

    @Test
    public void testDelete16() {
        bst.delete(16);
        String result = bst.toString();
        System.out.println("\nTree after deleting key 16 (Level-Order): ");
        assertEquals("7 6 10 5 13 3 12 18 15 23 20", result);
    }

    @Test
    public void testHeight() {
        assertEquals(6, bst.height());
    }

}