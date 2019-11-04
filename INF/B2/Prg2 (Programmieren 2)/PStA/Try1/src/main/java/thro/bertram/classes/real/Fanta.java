package thro.bertram.classes.real;

import thro.bertram.classes.abstr.SoftDrink;

public class Fanta extends SoftDrink
{
    public String getProductName()
    {
        return "Fanta";
    }

    @Override
    public String getBrand() {
        return "Coca Cola Corporation";
    }

    @Override
    public double getPricePerBottle() {
        return 0.99;
    }

    @Override
    public double getPricePerBox() {
        return 10.99;
    }

    @Override
    public int getBottlesPerBox() {
        return 12;
    }

    @Override
    public float getVolume() {
        return 1.0f;
    }

}
