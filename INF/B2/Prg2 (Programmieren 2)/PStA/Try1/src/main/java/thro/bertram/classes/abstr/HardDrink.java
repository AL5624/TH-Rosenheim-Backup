package thro.bertram.classes.abstr;

public abstract class HardDrink extends Drink {
    public String getPackaging() {
        return "Glas";
    }

    public boolean hasCoffein() {
        return false;
    }

    public abstract int getAlcoholPercentage();
}
