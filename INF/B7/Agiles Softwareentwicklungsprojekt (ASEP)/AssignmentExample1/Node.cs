namespace ExampleOptimizers;
internal class Node
{
    private Vector2D position;

    public Vector2D Position { get => position; set => position = value; }

    public Node(Vector2D position)
    {
        Position = position;
    }
}
