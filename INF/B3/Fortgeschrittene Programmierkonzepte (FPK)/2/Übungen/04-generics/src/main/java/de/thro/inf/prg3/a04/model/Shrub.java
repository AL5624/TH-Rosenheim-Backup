package de.thro.inf.prg3.a04.model;

public class Shrub extends Plant {
    public Shrub(String family, String name, double height) throws IllegalArgumentException {
        super(family, name, height, PlantColor.GREEN);
    }
}
