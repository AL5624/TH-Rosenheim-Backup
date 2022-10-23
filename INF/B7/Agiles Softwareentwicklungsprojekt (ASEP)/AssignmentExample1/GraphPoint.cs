namespace AssignmentExample1;
internal class GraphPoint
{
    private Vector2D position;
    public Vector2D Position => position;

    private ConsoleColor color;
    public ConsoleColor Color { get => color; set => color = value; }

    private string label;
    public string Label { get => label; set => label = value; }

    private Vector2D normalized;
    public Vector2D Normalized { get => normalized; set => normalized = value; }

    public GraphPoint(Vector2D position, ConsoleColor color = ConsoleColor.Red, string label = "")
    {
        this.position = position;
        this.color = color;
        this.label = label;
    }
}
