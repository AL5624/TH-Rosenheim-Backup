package de.thro.inf.prg3.a05.tests.collections;

import de.thro.inf.prg3.a05.PlantBed;
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

import java.sql.SQLOutput;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlantBedTest
{
    PlantBed Test = new PlantBed();

    @BeforeEach
    void setup()
    {
        Shrub shrub = new Shrub(3.0, "Shrub", "Shrub");
        Flower Blume_1 = new Flower(PlantColour.YELLOW, 1.5, "Blumen", "Sonnenblume");
        Test.add(shrub);
        Test.add(Blume_1);
    }

    @Test
    public void addTest()
    {
        Shrub shrub = new Shrub(3.0, "Shrub", "Shrub");
        Flower Blume_1 = new Flower(PlantColour.YELLOW, 1.5, "Blumen", "Sonnenblume");
        PlantBed Test2 = new PlantBed();
        Test2.add(shrub);
        Test2.add(Blume_1);
    }

    @Test
    public void sizeTest()
    {
        assertEquals(2, Test.size());
    }

    @Test
    public void getPlantsByColourTest()
    {
        SimpleList<Plant> p = new SimpleListImpl();
        p = Test.getPlantsByColour(PlantColour.YELLOW);
        assertEquals(1, Test.getPlantsByColour(PlantColour.YELLOW).size());
        for(Plant t: p)
        {
            assertEquals(PlantColour.YELLOW, t.getColour());
        }
    }

}
