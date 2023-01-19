namespace ExampleOptimizers;
internal class Command
{
    public static List<Command> GlobalCommands = new()
    {
        new Command("add", new List<Parameter>()
        {
            new Parameter("worker"),
            new Parameter("task")
        })
    };

    private string key;
    // possible parameters
    private List<Parameter>? parameters;

    private Command(string key, List<Parameter>? parameters = null)
    {
        this.key = key;
        this.parameters = parameters;
    }

    private void AddWorker()
    {

    }
}
