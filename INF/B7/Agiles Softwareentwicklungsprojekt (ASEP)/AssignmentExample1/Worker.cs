namespace ExampleOptimizers;
internal class Worker
{
    private Node node;
    public Node Node { get => node; set => SetNode(value); }

    public List<Task> Tasks;

    public Worker(Node node)
    {
        this.node = node;
        node.Workers.Add(this);
    }

    private void SetNode(Node node)
    {
        this.node.Workers.Remove(this);
        node.Workers.Add(this);

        this.node = node;
    }
}
