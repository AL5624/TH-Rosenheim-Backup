namespace ExampleOptimizers;
internal class Worker
{
    private Node node;
    public Node Node { get => node; set => node = value; }

    public Worker(Node node)
    {
        this.node = node;
    }

    public uint GetNumberOfAssignableTasks()
    {
        return 1;
    }

    public double AlterCosts(Task task, double cost)
    {
        if (cost < task.future)
            return task.future;

        return cost;
    }

    public bool IsCompatibleWith(Task task)
    {
        return Vector2D.Distance(this.Node.Position, task.Node.Position) > task.future;
    }
}
