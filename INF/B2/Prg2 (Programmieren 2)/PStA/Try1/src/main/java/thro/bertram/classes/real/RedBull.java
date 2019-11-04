package thro.bertram.classes.real;

import thro.bertram.classes.abstr.SoftDrink;

public class RedBull extends SoftDrink
{
    @Override
    public String getProductName() {
        return "Red Bull Classic";
    }

    @Override
    public String getBrand() {
        return "Red Bull";
    }

    @Override
    public float getVolume() {
        return 0.33f;
    }

    @Override
    public boolean hasCoffein() {
        return true;
    }

    @Override
    public double getPricePerBottle() {
        return 1.25;
    }

    @Override
    public String getPackaging() {
        return "Can";
    }

    @Override
    public double getPricePerBox() {
        return 11.95;
    }

    @Override
    public int getBottlesPerBox() {
        return 12;
    }
}
