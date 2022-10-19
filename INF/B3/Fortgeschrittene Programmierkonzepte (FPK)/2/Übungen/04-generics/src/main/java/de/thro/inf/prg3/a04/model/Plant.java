package de.thro.inf.prg3.a04.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class Plant implements Comparable<Plant> {
    private final String name;
    private final String family;
    private final double height;
    private final PlantColor color;

    public Plant(String family, String name, double height, PlantColor color) throws IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("please set a valid name");
        }

        if (family == null || family.equals("")) {
            throw new IllegalArgumentException("please set a valid family");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("please set a valid height");
        }

        this.name = name;
        this.family = family;
        this.height = height;
        this.color = color;
    }

    @Override
    public int compareTo(@NonNull Plant other) {
        double result = this.getHeight() - other.getHeight();
        return result == 0 ? 0 : result > 0 ? 1 : -1;
    }
}
