package de.thro.inf.prg3.a05.tests.collections;

import de.thro.inf.prg3.a05.collections.SimpleFilter;
import de.thro.inf.prg3.a05.collections.SimpleList;
import de.thro.inf.prg3.a05.collections.SimpleListImpl;
import de.thro.inf.prg3.a05.model.Flower;
import de.thro.inf.prg3.a05.model.Plant;
import de.thro.inf.prg3.a05.model.PlantColour;
import de.thro.inf.prg3.a05.model.Shrub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlantTests
{
    @BeforeEach
    void setup()
    {
        Shrub shrub = new Shrub(3.0, "Shrub", "Shrub");
        Flower Blume_1 = new Flower(PlantColour.YELLOW, 1.5, "Blumen", "Sonnenblume");

    }

    @Test
    public void FlowerTest()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Flower Blume_Fehler = new Flower(PlantColour.GREEN, 5.5, "Blumen", "Test");
        });

    }
}
