package de.thro.inf.prg3.a04.model;

import de.thro.inf.prg3.a04.collections.SimpleList;
import de.thro.inf.prg3.a04.collections.SimpleListInterface;

public class PlantBed<T extends Plant> {
    private SimpleList<T> plants = new SimpleList<>();

    public void add(T p)
    {
        plants.add(p);
    }

    public int size()
    {
        return plants.size();
    }

    public SimpleListInterface<T> getPlantsByColor(PlantColor color)
    {
        return plants.filter(p -> p.getColor() == color);
    }

    public SimpleListInterface<T> getPlants()
    {
        return plants;
    }
}
