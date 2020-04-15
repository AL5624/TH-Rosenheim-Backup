package training.kata.roman;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RomanTest
{
    @Test
    public void testIshouldReturn1()
    {
        assertThat(Roman.romanToArabic("I"), equalTo(1));
    }

    @Test
    public void testVshouldReturn5()
    {
        assertThat(Roman.romanToArabic("V"), equalTo(5));
    }

    @Test
    public void testXshouldReturn10()
    {
        assertThat(Roman.romanToArabic("X"), equalTo(10));
    }

    @Test
    public void testLshouldReturn50()
    {
        assertThat(Roman.romanToArabic("L"), equalTo(50));
    }

    @Test
    public void testCshouldReturn100()
    {
        assertThat(Roman.romanToArabic("C"), equalTo(100));
    }

    @Test
    public void testDshouldReturn500()
    {
        assertThat(Roman.romanToArabic("D"), equalTo(500));
    }

    @Test
    public void testMshouldReturn1000()
    {
        assertThat(Roman.romanToArabic("M"), equalTo(1000));
    }

    @Test
    public void testIIshouldReturn2()
    {
        assertThat(Roman.romanToArabic("II"), equalTo(2));
    }
}