import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

    Contact C1 = new Contact("Anton");
    Contact C2 = new Contact("Paul", 138725043);

    @Test
    public void ContactTest(){
        assertEquals(C2.getName(), "Paul");
    }

    @Test
    public void getContactCounterTest() {
    }

    @Test
    public void getNameTest() {
    }

    @Test
    public void getPhoneTest() {
    }

    @Test
    public void getNextTest() {
    }

    @Test
    public void toStringTest() {

    }

    @Test
    public void setNextTest() {
    }
}