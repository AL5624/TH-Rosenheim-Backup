using Google.OrTools.Sat;

namespace ExampleOptimizers;
internal class SatExample
{
    public static void Run()
    {
        CpSatPerformance();
    }

    private static void CpSatPerformance()
    {
        int num = 500;
        int numWorkers = num;
        int numTasks = num;

        Node[] nodes = new Node[numWorkers + numTasks];
        Utils.FillArray(nodes, (i) => new Node(RandomVector2D(0, num)));

        Worker[] workers = new Worker[numWorkers];
        Utils.FillArray(workers, (i) => new Worker(nodes[ i ]));

        Task[] tasks = new Task[numTasks];
        Utils.FillArray(tasks, (i) => new Task(nodes[ i + numWorkers ]));

        Console.WriteLine("Starting Watcher...");
        var watch = System.Diagnostics.Stopwatch.StartNew();

        // Model.
        CpModel model = new CpModel();

        // Objective
        LinearExprBuilder obj = LinearExpr.NewBuilder();

        // Variables.
        BoolVar[,] x = new BoolVar[numWorkers, numTasks];
        // Variables in a 1-dim array.
        for (int worker = 0; worker < numWorkers; ++worker)
        {
            List<ILiteral> taskList = new List<ILiteral>();
            for (int task = 0; task < numTasks; ++task)
            {
                double cost = Vector2D.Distance(workers[ worker ].Node.Position, tasks[ task ].Node.Position);
                x[ worker, task ] = model.NewBoolVar($"worker_{worker}_task_{task}");
                taskList.Add(x[ worker, task ]);

                obj.AddTerm((IntVar) x[ worker, task ], (long) cost);
            }

            if (numWorkers < numTasks)
            {
                model.AddAtMostOne(taskList);
            }
            else
            {
                model.AddExactlyOne(taskList);
            }
        }

        // Each task is assigned to exactly one worker.
        for (int task = 0; task < numTasks; ++task)
        {
            List<ILiteral> workerList = new List<ILiteral>();
            for (int worker = 0; worker < numWorkers; ++worker)
            {
                workerList.Add(x[ worker, task ]);
            }
            if (numWorkers >= numTasks)
            {
                model.AddExactlyOne(workerList);
            }
            else
            {
                model.AddAtMostOne(workerList);
            }
        }

        model.Minimize(obj);

        // Solve
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.Solve(model);

        watch.Stop();
        long elapsedMs = watch.ElapsedMilliseconds;
        Console.WriteLine("Duration: " + elapsedMs + " ms");

        Console.WriteLine($"Solve status: {status}");

        // Print solution.
        // Check that the problem has a feasible solution.
        if (status == CpSolverStatus.Optimal || status == CpSolverStatus.Feasible)
        {
            Console.WriteLine($"Total cost: {solver.ObjectiveValue}\n");
        }
        else
        {
            Console.WriteLine("No solution found.");
        }

        Console.WriteLine("Statistics");
        Console.WriteLine($"  - conflicts : {solver.NumConflicts()}");
        Console.WriteLine($"  - branches  : {solver.NumBranches()}");
        Console.WriteLine($"  - wall time : {solver.WallTime()}s");
    }

    private static Vector2D RandomVector2D(int lb = 0, int up = 10)
    {
        return Vector2D.RandomPosition(lb, up);
    }

    private static void CpSatExample()
    {
        // Data.
        int[,] costs = {
        { 90, 80, 75, 70 }, { 35, 85, 55, 65 }, { 125, 95, 90, 95 }, { 45, 110, 95, 115 }, { 50, 100, 90, 100 } };
        int numWorkers = costs.GetLength(0);
        int numTasks = costs.GetLength(1);

        // Model.
        CpModel model = new CpModel();

        // Variables.
        BoolVar[,] x = new BoolVar[numWorkers, numTasks];
        // Variables in a 1-dim array.
        for (int worker = 0; worker < numWorkers; ++worker)
        {
            for (int task = 0; task < numTasks; ++task)
            {
                x[ worker, task ] = model.NewBoolVar($"worker_{worker}_task_{task}");
            }
        }

        // Constraints
        // Each worker is assigned to at most one task.
        for (int worker = 0; worker < numWorkers; ++worker)
        {
            List<ILiteral> tasks = new List<ILiteral>();
            for (int task = 0; task < numTasks; ++task)
            {
                tasks.Add(x[ worker, task ]);
            }
            model.AddAtMostOne(tasks);
        }

        // Each task is assigned to exactly one worker.
        for (int task = 0; task < numTasks; ++task)
        {
            List<ILiteral> workers = new List<ILiteral>();
            for (int worker = 0; worker < numWorkers; ++worker)
            {
                workers.Add(x[ worker, task ]);
            }
            model.AddExactlyOne(workers);
        }

        // Objective
        LinearExprBuilder obj = Google.OrTools.Sat.LinearExpr.NewBuilder();
        for (int worker = 0; worker < numWorkers; ++worker)
        {
            for (int task = 0; task < numTasks; ++task)
            {
                obj.AddTerm((IntVar) x[ worker, task ], costs[ worker, task ]);
            }
        }
        model.Minimize(obj);

        // Solve
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.Solve(model);
        Console.WriteLine($"Solve status: {status}");

        // Print solution.
        // Check that the problem has a feasible solution.
        if (status == CpSolverStatus.Optimal || status == CpSolverStatus.Feasible)
        {
            Console.WriteLine($"Total cost: {solver.ObjectiveValue}\n");
            for (int i = 0; i < numWorkers; ++i)
            {
                for (int j = 0; j < numTasks; ++j)
                {
                    if (solver.Value(x[ i, j ]) > 0.5)
                    {
                        Console.WriteLine($"Worker {i} assigned to task {j}. Cost: {costs[ i, j ]}");
                    }
                }
            }
        }
        else
        {
            Console.WriteLine("No solution found.");
        }

        Console.WriteLine("Statistics");
        Console.WriteLine($"  - conflicts : {solver.NumConflicts()}");
        Console.WriteLine($"  - branches  : {solver.NumBranches()}");
        Console.WriteLine($"  - wall time : {solver.WallTime()}s");
    }

}
