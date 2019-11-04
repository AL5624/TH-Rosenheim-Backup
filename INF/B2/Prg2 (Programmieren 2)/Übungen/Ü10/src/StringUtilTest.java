import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void string2StringArrayTest() {
        String s = "hans faehrt     auto";
        String [] p = StringUtil.string2StringArray(s);
        String [] c = {"auto", "faehrt", "hans"};
        assertArrayEquals(c, p);
    }

    @Test
    public void convertArrayToStringMethodTest() {
        String s = "hans faehrt     auto";
        String [] p = StringUtil.string2StringArray(s);
        s = StringUtil.stringArray2String(p);
        assertEquals("auto faehrt hans", s);
    }


    @Test
    public void areAnagramsTest() {
        assertEquals(true, StringUtil.areAnagrams2("Debit Card", "Bad Credit"));
        assertEquals(true, StringUtil.areAnagrams2("derbe Hotline", "Bohlen, Dieter"));
        assertEquals(false, StringUtil.areAnagrams2("haus", "haus"));
        assertEquals(false, StringUtil.areAnagrams2("joy", "enjoy"));

        assertEquals(true, StringUtil.areAnagrams("Debit Card", "Bad Credit"));
        assertEquals(true, StringUtil.areAnagrams("derbe Hotline", "Bohlen, Dieter"));
        assertEquals(false, StringUtil.areAnagrams("haus", "haus"));
        assertEquals(false, StringUtil.areAnagrams("joy", "enjoy"));
    }
}