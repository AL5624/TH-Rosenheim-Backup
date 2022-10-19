package de.thro.inf.prg3.a04.utils;

import de.thro.inf.prg3.a04.collections.SimpleList;
import de.thro.inf.prg3.a04.model.Plant;
import de.thro.inf.prg3.a04.model.PlantBed;
import de.thro.inf.prg3.a04.model.PlantColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class PlantBedUtility {

    public static <T extends Plant> Map<PlantColor, SimpleList<T>> splitBedByColor(PlantBed<T> bed) {
        Map<PlantColor, SimpleList<T>> map = new HashMap<>();
        for (PlantColor color : PlantColor.values()) {
            map.put(color, bed.getPlantsByColor(color));
        }

        return map;
    }

    public static <T> void pecs(SimpleList<? super T> dest, SimpleList<? extends T> src) {
        src.forEach(dest::add);
    }

    public static <T> void pecs_with_filter(SimpleList<? super T> dest, SimpleList<? extends T> src, Predicate<T> predicate) {
        src.forEach(item -> {
            if (predicate.test(item)) {
                dest.add(item);
            }
        });
    }

}
