package de.thro.inf.prg3.a04.collections;

import de.thro.inf.prg3.a04.model.Flower;
import de.thro.inf.prg3.a04.model.Plant;
import de.thro.inf.prg3.a04.model.PlantColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsUtilityTest {
    private SimpleListInterface<Plant> flowerBed;

    @BeforeEach
    void setup() {
        flowerBed = new SimpleList<>();

        flowerBed.add(new Flower("Rosa", "Golden Celebration", 0.4, PlantColor.YELLOW));
        flowerBed.add(new Flower("Rosa", "Abracadabra", 0.5, PlantColor.RED));
        flowerBed.add(new Flower("Rosa", "Golden Celebration", 0.3, PlantColor.YELLOW));
        flowerBed.add(new Flower("Rosa", "Golden Celebration", 0.35, PlantColor.YELLOW));
        flowerBed.add(new Flower("Rosa", "Abracadabra", 0.35, PlantColor.RED));
        flowerBed.add(new Flower("Rosa", "Rosa chinensis", 0.35, PlantColor.ORANGE));
        flowerBed.add(new Flower("Tulip", "Curly Sue", 0.35, PlantColor.VIOLETTE));
    }

    @Test
    void testSortFlowers() {
        CollectionsUtility.sort(flowerBed);

        double lastHeight = 0.0;
        for (Plant f : flowerBed) {
            assertTrue(f.getHeight() >= lastHeight);
            lastHeight = f.getHeight();
        }
    }
}