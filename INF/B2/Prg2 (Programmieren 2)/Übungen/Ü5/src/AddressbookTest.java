import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressbookTest {

    Addressbook Addressbuch = new Addressbook();

    Contact Kontakt1 = new Contact("Anton", 017672307774);
    Contact Kontakt2 = new Contact("Paul");
    Contact Kontakt3 = new Contact("Moritz");

    @Test
    public void idTest()
    {
        assertEquals(3, Kontakt1.getId());
    }

@Test
public void insertFrontTest() {
    Addressbuch.insertToFront(Kontakt1);

    assertEquals(Addressbuch.getFirst(), Kontakt1);

    Addressbuch.insertToFront(Kontakt2);

    assertEquals(Addressbuch.getFirst(), Kontakt2);

    Addressbuch.insertToFront(Kontakt3);

    assertEquals(Addressbuch.getFirst(), Kontakt3);


/*@Test
public void deleteTest()*/

    Addressbuch.delete(Kontakt2);

    assertEquals(Addressbuch.getFirst(), Kontakt3);

    assertEquals(Addressbuch.getFirst().getNext(), Kontakt1);

    assertEquals(Addressbuch.getFirst().getNext().getNext(), null);

}


}