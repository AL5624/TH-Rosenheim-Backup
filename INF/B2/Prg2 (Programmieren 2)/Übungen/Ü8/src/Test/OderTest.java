import org.junit.Test;

import static org.junit.Assert.*;

public class OderTest
{
    Oder baum = new Oder();
    Basisereignis a = new Basisereignis (false);
    Basisereignis b = new Basisereignis (true);
    Basisereignis c = new Basisereignis (true);

    @Test
    public void getWert()
    {
        assertEquals("Baum ohne Unterknoten", false, baum.getWert());
        baum.addKnoten(a).addKnoten(b);
        assertEquals("false OR true", true, baum.getWert());
        baum.ClearAllKnoten();
        baum.addKnoten(a).addKnoten(a);
        assertEquals("false OR false", false, baum.getWert());
        baum.ClearAllKnoten();
        baum.addKnoten(c).addKnoten(a);
        assertEquals("ture OR false", true, baum.getWert());
        baum.ClearAllKnoten();
        baum.addKnoten(b).addKnoten(c);
        assertEquals("true OR true", true, baum.getWert());

    }
}