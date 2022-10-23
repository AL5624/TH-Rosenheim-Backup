namespace AssignmentExample1;
internal class Node
{
    private Vector2D position;

    public Vector2D Position { get => position; set => position = value; }

    private List<Worker> workers = new();
    public List<Worker> Workers { get => workers; set => workers = value; }

    private List<Task> tasks = new();
    public List<Task> Tasks { get => tasks; set => tasks = value; }

    public Node(Vector2D position)
    {
        Position = position;
    }
}
