package de.thro.inf.prg3.a05.model;

public class Shrub extends Plant
{
    public Shrub(double height, String family, String name)
    {
        super(height, family, name);
        this.colour = PlantColour.GREEN;
    }
}
