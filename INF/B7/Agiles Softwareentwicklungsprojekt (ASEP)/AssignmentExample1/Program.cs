using Google.OrTools.LinearSolver;

namespace AssignmentExample1;

internal class Program
{
    private static void Main(string[] args)
    {
        int numWorkers = 2;
        int numTasks = 2;

        if (args.Length > 0)
        {
            int.TryParse(args[ 0 ], out numWorkers);
        }

        if (args.Length > 1)
        {
            int.TryParse(args[ 1 ], out numTasks);
        }

        FixedExampleOfMeeting();
    }

    private static void FixedExampleOfMeeting()
    {
        Worker[] workers =
        {
            new Worker(new Vector2D(10, 10)),
            new Worker(new Vector2D(0, 5))
        };

        Task[] tasks =
        {
            new Task(new Vector2D(0, 10)),
            new Task(new Vector2D(0, 0))
        };

        PrintPositionTable(workers, tasks);

        double[,] costTable = GetCostTable(workers, tasks);

        Solve(costTable);
    }

    private static void ExampleWithRandomVectors(int numWorkers = 2, int numTasks = 2)
    {
        Worker[] workers = new Worker[numWorkers];
        Task[] tasks = new Task[numTasks];

        Utils.InitArray(workers, (i) => new Worker(RandomVector2D()));
        Utils.InitArray(tasks, (i) => new Task(RandomVector2D()));

        PrintPositionTable(workers, tasks);

        double[,] costTable = GetCostTable(workers, tasks);

        Solve(costTable);
    }

    private static Vector2D RandomVector2D()
    {
        return Vector2D.RandomPosition();
    }

    private static void PrintPositionTable(Worker[] workers, Task[] tasks)
    {
        double[,] tableWorkers = new double[workers.Length, 2];
        double[,] tableTasks = new double[tasks.Length, 2];
        string [] header = { "", "X", "Y" };
        List<Node> nodes = new();

        for (int i = 0; i < workers.Length; i++)
        {
            nodes.Add(workers[ i ]);
            tableWorkers[ i, 0 ] = workers[ i ].Position.X;
            tableWorkers[ i, 1 ] = workers[ i ].Position.Y;
        }

        Console.WriteLine();
        Console.WriteLine("Worker Positions:");
        Console.WriteLine();

        Utils.PrintTable(tableWorkers, header);

        for (int i = 0; i < tasks.Length; i++)
        {
            nodes.Add(tasks[ i ]);
            tableTasks[ i, 0 ] = tasks[ i ].Position.X;
            tableTasks[ i, 1 ] = tasks[ i ].Position.Y;
        }

        Console.WriteLine();
        Console.WriteLine("Task Positions:");
        Console.WriteLine();

        Utils.PrintTable(tableTasks, header, "Task");
        Utils.DrawChart(nodes);

    }

    private static double[,] GetCostTable(Worker[] workers, Task[] tasks)
    {
        double[,] costs = new double[workers.Length, tasks.Length];

        for (int i = 0; i < workers.Length; i++)
        {
            Worker worker = workers[i];
            for (int j = 0; j < tasks.Length; j++)
            {
                Task task = tasks[j];
                costs[ i, j ] = Vector2D.Distance(worker.Position, task.Position);
            }
        }

        Console.WriteLine();
        Console.WriteLine("Costs Table:");
        Console.WriteLine();

        Utils.PrintTable(costs);

        return costs;
    }

    private static void Solve(double[,] costs)
    {
        int numWorkers = costs.GetLength(0);
        int numTasks = costs.GetLength(1);

        Solver solver = Solver.CreateSolver("SCIP");
        if (solver is null)
        {
            return;
        }

        // x[i, j] is an array of 0-1 variables, which will be 1
        // if worker i is assigned to task j.
        Variable[,] assignments = new Variable[numWorkers, numTasks];
        for (int i = 0; i < numWorkers; ++i)
        {
            for (int j = 0; j < numTasks; ++j)
            {
                assignments[ i, j ] = solver.MakeIntVar(0, 1, $"worker_{i}_task_{j}");
            }
        }

        // Each worker is assigned to at most one task.
        for (int i = 0; i < numWorkers; ++i)
        {
            Constraint constraint = solver.MakeConstraint(0, 1, "");
            for (int j = 0; j < numTasks; ++j)
            {
                constraint.SetCoefficient(assignments[ i, j ], 1);
            }
        }

        // Each task is assigned to exactly one worker.
        for (int j = 0; j < numTasks; ++j)
        {
            Constraint constraint = solver.MakeConstraint(1, 1, "");
            for (int i = 0; i < numWorkers; ++i)
            {
                constraint.SetCoefficient(assignments[ i, j ], 1);
            }
        }

        Objective objective = solver.Objective();
        for (int i = 0; i < numWorkers; ++i)
        {
            for (int j = 0; j < numTasks; ++j)
            {
                objective.SetCoefficient(assignments[ i, j ], costs[ i, j ]);
            }
        }

        objective.SetMinimization();

        Solver.ResultStatus resultStatus = solver.Solve();

        // Check that the problem has a feasible solution.
        if (resultStatus is Solver.ResultStatus.OPTIMAL or Solver.ResultStatus.FEASIBLE)
        {
            double[,] table = new double[assignments.GetLength(0), assignments.GetLength(1)];
            for (int i = 0; i < assignments.GetLength(0); i++)
            {
                for (int j = 0; j < assignments.GetLength(1); j++)
                {
                    double value = assignments[ i, j ].SolutionValue();
                    table[ i, j ] = value;
                }
            }

            Console.WriteLine();
            Console.WriteLine("Assignment Table:");
            Console.WriteLine();
            Utils.PrintTable(table);
            Console.WriteLine();

            Console.WriteLine($"Total cost: {solver.Objective().Value():#.##}\n");
            for (int i = 0; i < numWorkers; ++i)
            {
                for (int j = 0; j < numTasks; ++j)
                {
                    // Test if x[i, j] is 0 or 1 (with tolerance for floating point
                    // arithmetic).
                    if (assignments[ i, j ].SolutionValue() > 0.5)
                    {
                        Console.WriteLine($"Worker {i} assigned to Task {j}. Cost: {costs[ i, j ]:#.##}");
                    }
                }
            }
        }
        else
        {
            Console.WriteLine("No solution found.");
        }
    }

    private void Example()
    {
        double[,] costs = {
            { 90, 80, 75, 70 }, { 35, 85, 55, 65 }, { 125, 95, 90, 95 }, { 45, 110, 95, 115 }, { 50, 100, 90, 100 },
        };

        Solve(costs);
    }
}