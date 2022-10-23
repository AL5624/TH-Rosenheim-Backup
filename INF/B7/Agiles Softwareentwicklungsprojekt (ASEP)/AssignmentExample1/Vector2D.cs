namespace ExampleOptimizers;

internal class Vector2D
{
    private double x;
    public double X { get => x; set => x = value; }

    private double y;
    public double Y { get => y; set => y = value; }

    public Vector2D(double x = 0, double y = 0)
    {
        this.X = x;
        this.Y = y;
    }

    public static Vector2D RandomPosition(int lb = 0, int ub = 10)
    {
        return new Vector2D(RandomInt(lb, ub), RandomInt(lb, ub));
    }

    private static int RandomInt(int lb, int ub)
    {
        return new Random().Next(lb, ub);
    }

    public static double Distance(Vector2D v1, Vector2D v2)
    {
        double x =  v2.X- v1.X;
        double y = v2.Y - v1.Y;

        double result = Math.Sqrt(Math.Pow(x, 2) + Math.Pow(y, 2));

        return result;
    }
}
