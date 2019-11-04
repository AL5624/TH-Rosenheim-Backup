package thro.bertram.classes.abstr;

public abstract class SoftDrink extends Drink {
    public String getPackaging() {
        return "Plastic";
    }

    public boolean hasCoffein() {
        return false;
    }

}
