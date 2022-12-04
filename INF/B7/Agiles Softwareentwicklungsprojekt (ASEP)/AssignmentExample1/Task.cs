namespace ExampleOptimizers;
internal class Task
{
    private Node node;
    public Node Node { get => node; set => node = value; }

    public int notTakenRounds = 0;

    private int percentage = 100;

    private int min = Utils.RandomInt(1, 3);

    public int Priority = 1;
    public string Name;

    public int future = Utils.RandomInt(0, 3) == 1 ? 5 : 0;

    public Task(Node node, string name)
    {
        this.node = node;
        this.Name = name;
    }

    public double GetMinimalRequiredWorkers()
    {
        return 1;
    }

    public uint GetAdditionalOptionalNumberOfWorkers()
    {
        return 0;
    }

    public double AlterCosts(double cost)
    {
        if (cost > this.future)
            for (int i = 0;  i < this.notTakenRounds; i++)
            {
                cost *= 0.5;
            }

        return cost;
    }

    public void NotTaken()
    {
        ++this.notTakenRounds;

        if (this.future > 0)
        {
            --this.future;
        }
        else
        {
            ++this.Priority;
        }
    }
}
