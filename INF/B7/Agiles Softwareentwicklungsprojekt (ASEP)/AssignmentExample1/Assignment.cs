using Google.OrTools.LinearSolver;
using Google.OrTools.Sat;

namespace ExampleOptimizers;

internal class Assignment
{
	public Worker worker;
	public Task task;
	public Variable variable;
    public double cost;

	public Assignment(Worker worker, Task task, Variable variable, double cost)
	{
		this.worker = worker;
		this.task = task;
		this.variable = variable;
		this.cost = cost;
	}
}
