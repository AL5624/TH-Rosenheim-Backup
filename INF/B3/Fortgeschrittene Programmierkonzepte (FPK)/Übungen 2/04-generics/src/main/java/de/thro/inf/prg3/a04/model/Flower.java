package de.thro.inf.prg3.a04.model;

public class Flower extends Plant {
    public Flower (String family, String name, double height, PlantColor plantColor) throws IllegalArgumentException
    {
        if (throwConstructorException(name, family, height, plantColor))
        {
            throw new IllegalArgumentException();
        }

        this.family = family;
        this.name = name;
        this.height = height;
        this.plantColor = plantColor;
    }

    private boolean throwConstructorException(String name, String family, double height, PlantColor plantColor)
    {
        return height <= 0 || name == null || family == null || plantColor == PlantColor.GREEN;
    }
}
