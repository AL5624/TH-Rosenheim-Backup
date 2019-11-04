public class Shape
{
    private String colour = "red";

    public double getLineWidth() {
        return lineWidth;
    }

    final private double lineWidth = 2 * Math.random();

    public Shape() {}

    public String getColour()
    {
        return colour;
    }

    public Shape(String colour)
    {
        this.colour = colour;
    }

    public String toString()
    {
        return "Colour: " + colour + " - " + "lineWidth: " + lineWidth;
    }


}
