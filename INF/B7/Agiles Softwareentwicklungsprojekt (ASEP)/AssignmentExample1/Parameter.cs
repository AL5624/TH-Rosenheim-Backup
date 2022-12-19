namespace ExampleOptimizers;
internal class Parameter
{
    private string _name;
    public string Name => _name;

    // possible parameters
    private List<Parameter>? _parameters;
    internal List<Parameter>? Parameters => _parameters;

    public Parameter(string name, List<Parameter>? parameters = null, Func<void>? func)
    {
        _name = name;
        _parameters = parameters;
    }
}
