namespace AssignmentExample1;
internal class Node
{
    private Vector2D position;

    public Vector2D Position { get => position; set => position = value; }
    public ConsoleColor ConsoleColor { get => consoleColor; set => consoleColor = value; }

    private ConsoleColor consoleColor;

    public Node(Vector2D position, ConsoleColor consoleColor)
    {
        Position = position;
        ConsoleColor = consoleColor;
    }
}
