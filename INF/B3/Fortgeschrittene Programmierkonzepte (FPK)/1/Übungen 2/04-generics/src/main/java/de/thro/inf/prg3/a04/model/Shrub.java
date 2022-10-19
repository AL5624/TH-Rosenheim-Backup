package de.thro.inf.prg3.a04.model;

import de.thro.inf.prg3.a04.model.Plant;
import de.thro.inf.prg3.a04.model.PlantColor;

public class Shrub extends Plant {
    public Shrub (String family, String name, double height) throws IllegalArgumentException
    {
        if (throwConstructorException(name, family, height))
        {
            throw new IllegalArgumentException();
        }

        this.family = family;
        this.name = name;
        this.height = height;
        this.plantColor = PlantColor.GREEN;
    }

    private boolean throwConstructorException(String name, String family, double height)
    {
        return height <= 0 || name == null || family == null;
    }
}
