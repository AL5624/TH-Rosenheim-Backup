package de.thro.inf.prg3.a04.model;

public abstract class Plant implements Comparable<Plant> {
    protected double height;
    protected String family;
    protected String name;
    protected PlantColor plantColor;

    public double getHeight() {
        return height;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public PlantColor getColor() {
        return plantColor;
    }

    @Override
    public int compareTo(Plant o) {
        if (this.getHeight() > o.getHeight())
        {
            return -1;
        }
        else if (this.getHeight() < o.getHeight())
        {
            return 1;
        }

        return 0;
    }
}
