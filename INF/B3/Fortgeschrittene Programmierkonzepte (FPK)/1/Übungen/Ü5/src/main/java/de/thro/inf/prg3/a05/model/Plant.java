package de.thro.inf.prg3.a05.model;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Comparator;

public abstract class Plant implements Comparable<Plant>
{
    private double height;
    private String family;
    private String name;
    protected PlantColour colour = null;

    public Plant(double height, String family, String name)
    {
        this.height = height;
        this.family = family;
        this.name = name;
    }

    public double getHeight()
    {
        return this.height;
    }

    public String getFamily()
    {
        return this.family;
    }

    public String getName()
    {
        return this.name;
    }

    public PlantColour getColour()
    {
        return this.colour;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    @Override
    public int compareTo(Plant plant)
    {
        if(this.height < plant.height) return -1;
        if(this.height > plant.height) return 1;
        return 0;
        //return Double.compare(this.height, plant.height);
    }
}
