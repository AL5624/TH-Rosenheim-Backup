package training.lecture.gildedrose;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class GildedRoseTest
{
    private String name;
    private int    sellin;
    private int    quality;
    private String expected;

    public GildedRoseTest(String name, int sellin, int quality, String expected)
    {
        this.name     = name;
        this.sellin   = sellin;
        this.quality  = quality;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: testUpdateQuality(name {0}, sellin {1}, quality {2}, expected{3})")
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]{
                {"foo", 0, 0, "foo, -1, 0"}
        });
    }

    @Test
    public void testUpdateQuality()
    {
        Item[]     items = new Item[]{new Item(name, sellin, quality)};
        GildedRose app   = new GildedRose(items);
        app.updateQuality();
        assertEquals(expected, app.items[0].toString());
    }
}