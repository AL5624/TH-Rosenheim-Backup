public class Square extends Rectangle
{
    public Square(double side)
    {
        super(side, side);
    }

    public Square(double side, String colour)
    {
        super(side, side, colour);
    }

    public double getSide()
    {
        return getLength();
    }

    public void setSide(double side)
    {
        length = side;
        width = side;
    }

    @Override
    public void setLength(double length)
    {
        setSide(length);
    }

    @Override
    public void setWidth(double width)
    {
        setLength(width);
    }
}
