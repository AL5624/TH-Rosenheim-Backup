package training.kata.roman;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
public class RomanTest
{

    @Test
    public void romanToArabic()
    {
        assertThat(Roman.romanToArabic("I"), equalTo(1));
    }
}