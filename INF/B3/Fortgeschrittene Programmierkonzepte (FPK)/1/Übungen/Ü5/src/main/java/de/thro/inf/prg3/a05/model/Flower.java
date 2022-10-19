package de.thro.inf.prg3.a05.model;

public class Flower extends Plant
{
    public Flower(PlantColour colour, double height, String family, String name) throws IllegalArgumentException
    {
        super(height, family, name);
        if(colour == PlantColour.GREEN)
        {
            throw new IllegalArgumentException("Colour can't be green!");
        }
        else
        {
            this.colour = colour;
        }
    }
}
