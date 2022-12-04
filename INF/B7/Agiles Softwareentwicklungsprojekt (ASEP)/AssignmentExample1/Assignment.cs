using Google.OrTools.LinearSolver;
using Google.OrTools.Sat;

namespace ExampleOptimizers;

internal class Assignment
{
	public List<Worker> workers = new();
	public Task task;
	public Variable variable;
    public double cost;

	public Assignment(Worker worker, Task task, Variable variable, double cost)
	{
		this.workers.Add(worker);
		this.task = task;
		this.variable = variable;
		this.cost = cost;
	}
}
