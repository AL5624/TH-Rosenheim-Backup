import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest
{

    @Test(expected = MyException.class)
    public void m() throws MyException
    {
        Person a = new Person("a", 1);

        a.m(3);
    }
}