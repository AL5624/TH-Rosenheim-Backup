package thro.bertram.classes.real;

import thro.bertram.classes.abstr.Brands.CocaColaCorporation;

public class CocaCola extends CocaColaCorporation
{
    public String getProductName()
    {
        return "Coca Cola";
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

    @Override
    public boolean hasCoffein() {
        return true;
    }
}
