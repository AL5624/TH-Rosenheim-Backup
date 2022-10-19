package de.thro.inf.prg3.a04.model;

import lombok.AllArgsConstructor;

public class Flower extends Plant {
    public Flower(String family, String name, double height, PlantColor color) throws IllegalArgumentException {
        super(family, name, height, color);
        if (color == PlantColor.GREEN) {
            throw new IllegalArgumentException("Flowers can not be green");
        }
    }
}
