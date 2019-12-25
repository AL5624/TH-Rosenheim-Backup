package de.thro.inf.prg3.a05;

import de.thro.inf.prg3.a05.collections.SimpleFilter;
import de.thro.inf.prg3.a05.collections.SimpleList;
import de.thro.inf.prg3.a05.collections.SimpleListImpl;
import de.thro.inf.prg3.a05.model.Plant;
import de.thro.inf.prg3.a05.model.PlantColour;

public class PlantBed<T extends Plant>
{
    private SimpleList<T> plants;

    public PlantBed()
    {
        plants = new SimpleListImpl<>();
    }

    public void add(T plant)
    {
        this.plants.add(plant);
    }

    public int size()
    {
        return plants.size();
    }

    public SimpleList<T> getPlantsByColour(PlantColour colour)
    {
        return plants.filter(new SimpleFilter<T>()
        {
            @Override
            public boolean include(T item)
            {
                return item.getColour() == colour;
            }
        });
    }
}
