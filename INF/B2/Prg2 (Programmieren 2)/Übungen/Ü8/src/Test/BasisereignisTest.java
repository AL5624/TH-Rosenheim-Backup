import org.junit.Test;

import static org.junit.Assert.*;

public class BasisereignisTest
{

    Basisereignis a = new Basisereignis (false);
    Basisereignis b = new Basisereignis (true);
    Basisereignis c = new Basisereignis (true);

    @Test
    public void getWert()
    {
        assertEquals("Wert a", false, a.getWert());
        assertEquals("Wert b",true,  b.getWert());
        assertEquals("Wert c", true, c.getWert());
    }
}