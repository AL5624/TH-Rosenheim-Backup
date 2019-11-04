public class Rectangle extends Shape
{
    protected double length;
    protected double width;

    public Rectangle(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    public Rectangle(double length, double width, String colour)
    {
        super(colour);
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea()
    {
        return length * width;
    }

    public double getPerimeter()
    {
        return 2 * length + 2 * width;
    }

    @Override
    public String toString()
    {
        return "Ein Rechteck(Länge: " + length + " - Breite: " + width + ") ist eine From(Farbe: " + getColour() + " - Linienbreite: " + getLineWidth() + ")";
    }
}
