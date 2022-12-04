using Google.OrTools.LinearSolver;

namespace ExampleOptimizers;
internal class VariableWrapper
{
    public Worker worker;
    public Task task;
    public Variable assignment;
    public VariableWrapper(Variable assignment, Worker worker, Task task)
    {
        this.worker = worker;
        this.task = task;
        this.assignment = assignment;
    }

    public void Resolve()
    {
        if (this.assignment.SolutionValue() > 0.5)
        {
            this.worker.Task.Add(task);
        }
    }
}
