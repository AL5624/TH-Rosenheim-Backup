package de.thro.inf.prg3.a04.model;

import de.thro.inf.prg3.a04.collections.CollectionsUtility;
import de.thro.inf.prg3.a04.collections.SimpleList;
import de.thro.inf.prg3.a04.collections.SimpleListImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class FlowerTest {


    @Test
    void testCreateGreenFlower() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Flower("Rosa", "Abracadabra", 0.5, PlantColor.GREEN);
            }
        });
    }

    @Test
    void testCreateFlowerWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Flower("Rosa", "Abracadabra", 0.5, PlantColor.GREEN));
    }

    @Test
    void testCreateFlowerWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Flower("Rosa", "Abracadabra", -0.5, PlantColor.RED));
    }

    @Test
    void testGetCorrectColor() {
        Flower f = new Flower("Rosa", "Abracadabra", 0.5, PlantColor.RED);
        assertEquals(PlantColor.RED, f.getColor());
    }

    @Test
    void testGetCorrectHeight() {
        Flower f = new Flower("Rosa", "Abracadabra", 0.5, PlantColor.RED);
        assertEquals(0.5, f.getHeight(), 0.0000001);
    }
}