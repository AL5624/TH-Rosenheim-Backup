public class Circle extends Shape
{
    double radius;

    public Circle(double radius)
    {
        this.radius = radius;
    }

    public Circle(double radius, String colour)
    {
        super(colour);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea()
    {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter()
    {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString()
    {
        return "Ein Kreis(Radius: " + radius + " - Umfang: " + this.getPerimeter() + " - Flaecheninhalt: " + this.getArea() + ") ist eine Form(Farbe: " + this.getColour() + " - Linienbreite: " + this.getLineWidth() + ")";
    }

}
