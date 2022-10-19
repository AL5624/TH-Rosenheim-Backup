package de.thro.inf.prg3.a04.utils;

import de.thro.inf.prg3.a04.collections.SimpleList;
import de.thro.inf.prg3.a04.collections.SimpleListInterface;
import de.thro.inf.prg3.a04.model.Plant;
import de.thro.inf.prg3.a04.model.PlantBed;
import de.thro.inf.prg3.a04.model.PlantColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class PlantBedUtility {
    public static <T extends Plant> Map<PlantColor, SimpleListInterface<T>> splitBedByColor(PlantBed<T> bed)
    {
        Map<PlantColor, SimpleListInterface<T>> map = new HashMap<>();

        for(PlantColor color: PlantColor.values())
            map.put(color, bed.getPlantsByColor(color));

        return map;
    }

    public static <T extends Plant> void pecs(SimpleListInterface<? super T> dest, SimpleListInterface<? extends T> src)
    {
        PlantBedUtility.pecs_with_filter(dest, src, (Predicate<T>) t -> true);
    }

    public static <T extends Plant> void pecs_with_filter
            (SimpleListInterface<? super T> dest, SimpleListInterface<? extends T> src, Predicate<T> test)
    {
        for (T o: src)
        {
            if (test.test(o))
                dest.add(o);
        }
    }
}
