using Google.OrTools.LinearSolver;

namespace ExampleOptimizers;

internal class Assignment
{
	public Worker worker;
	public Task task;
	public Variable variable;
	public Assignment(Worker worker, Task task, Variable variable)
	{
		this.worker = worker;
		this.task = task;
		this.variable = variable;
	}
}
