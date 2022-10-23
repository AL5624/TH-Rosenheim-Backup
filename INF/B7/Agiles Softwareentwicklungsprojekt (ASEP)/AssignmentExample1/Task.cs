namespace ExampleOptimizers;
internal class Task
{
    private Node node;
    public Node Node { get => node; set => SetNode(value); }

    public Task(Node node)
    {
        this.node = node;
        node.Tasks.Add(this);
    }

    private void SetNode(Node node)
    {
        this.node.Tasks.Remove(this);
        node.Tasks.Add(this);

        this.node = node;
    }
}
