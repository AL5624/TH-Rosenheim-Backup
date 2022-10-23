﻿using Google.OrTools.LinearSolver;

namespace ExampleOptimizers;

internal class Program
{
    private static void Main(string[] args)
    {
        int num = 4;
        ExampleWithRandomVectors(num, num);
    }

    private static void FixedExampleOfMeeting(int numWorkers = 2, int numTasks = 2)
    {
        Node[] nodes =
        {
            new Node(new Vector2D(10, 10)),
            new Node(new Vector2D(0, 5)),
            new Node(new Vector2D(0, 10)),
            new Node(new Vector2D(0, 0))
        };

        Worker[] workers =
        {
            new Worker(nodes[0]),
            new Worker(nodes[1])
        };

        Task[] tasks =
        {
            new Task(nodes[2]),
            new Task(nodes[3])
        };

        PrintPositionTable(workers, tasks);

        List<GraphPoint> costGrahpPoints = new();

        double[,] costTable = GetCostTable(workers, tasks, costGrahpPoints);

        Solve(costTable, costGrahpPoints);
    }

    private static void ExampleWithRandomVectors(int numWorkers = 2, int numTasks = 2)
    {
        Node[] nodes = new Node[numWorkers + numTasks];
        Utils.FillArray(nodes, (i) => new Node(RandomVector2D()));

        Worker[] workers = new Worker[numWorkers];
        Utils.FillArray(workers, (i) => new Worker(nodes[ i ]));

        Task[] tasks = new Task[numTasks];
        Utils.FillArray(tasks, (i) => new Task(nodes[ i + numWorkers ]));

        PrintPositionTable(workers, tasks);

        List<GraphPoint> costGrahpPoints = new();

        double[,] costTable = GetCostTable(workers, tasks, costGrahpPoints);

        Solve(costTable, costGrahpPoints);
    }

    private static Vector2D RandomVector2D()
    {
        return Vector2D.RandomPosition();
    }

    private static void PrintPositionTable(Worker[] workers, Task[] tasks)
    {
        List<GraphPoint> points = new();

        double[,] tableWorkers = new double[ workers.Length, 2 ];
        Utils.FillArray2D(tableWorkers, (index) =>
        {
            points.Add(new GraphPoint(workers[ index ].Node.Position, ConsoleColor.Blue, $"W{index}"));
            return new double[] { workers[ index ].Node.Position.X, workers[ index ].Node.Position.Y };
        });

        double[,] tableTasks = new double[ tasks.Length, 2 ];
        Utils.FillArray2D(tableTasks, (index) =>
        {
            points.Add(new GraphPoint(tasks[ index ].Node.Position, ConsoleColor.Red, $"T{index}"));
            return new double[] { tasks[ index ].Node.Position.X, tasks[ index ].Node.Position.Y };
        });

        string [] header = { "", "X", "Y" };

        Console.WriteLine();
        Console.WriteLine("Worker Positions:");
        Console.WriteLine();

        Utils.PrintTable(tableWorkers, header);

        Console.WriteLine();
        Console.WriteLine("Task Positions:");
        Console.WriteLine();

        Utils.PrintTable(tableTasks, header, "Task");
        Utils.DrawChart(points);

    }

    private static double[,] GetCostTable(Worker[] workers, Task[] tasks, List<GraphPoint> costGrahpPoints)
    {
        double[,] costs = new double[ workers.Length, tasks.Length ];

        for (int workerIndex = 0; workerIndex < workers.Length; workerIndex++)
        {
            Worker worker = workers[workerIndex];
            costGrahpPoints.Add(new GraphPoint(new Vector2D(workerIndex, 0), ConsoleColor.Blue, $"W{workerIndex}"));

            for (int taskIndex = 0; taskIndex < tasks.Length; taskIndex++)
            {
                Task task = tasks[taskIndex];
                costs[ workerIndex, taskIndex ] = Vector2D.Distance(worker.Node.Position, task.Node.Position);
                costGrahpPoints.Add(new GraphPoint(new Vector2D(workerIndex, costs[ workerIndex, taskIndex ]), (ConsoleColor) taskIndex + 10, $"T{taskIndex} ({costs[ workerIndex, taskIndex ]:0.##})"));
            }
        }

        return costs;
    }

    private static void Solve(double[,] costs, List<GraphPoint>? costGrahpPoints = null)
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
            double[,] assignmentTable = new double[ assignments.GetLength(0) , assignments.GetLength(1) ];
            Utils.FillArray2D(assignmentTable, (i) =>
            {
                double[] tmp = new double[assignments.GetLength(1)];
                Utils.FillArray(tmp, (j) =>
                {
                    double value = assignments[ i, j ].SolutionValue();
                    double cost = costs[ i, j ];
                    if (value > 0.5 && costGrahpPoints is not null)
                    {
                        int index = (i * (numTasks + 1))  + j + 1;
                        if (cost < 1)
                        {
                            GraphPoint t = costGrahpPoints[ index ];
                            index = i * (numTasks + 1);
                            costGrahpPoints[ index ].Label = "[" + t.Label + "]";
                            costGrahpPoints[ index ].Color = t.Color;
                        }
                        else
                        {
                            costGrahpPoints[ index ].Label = "[" + costGrahpPoints[ index ].Label + "]";
                        }
                    }

                    return value;
                });

                return tmp;
            });

            Console.WriteLine();
            Console.WriteLine("Costs Table:");
            Console.WriteLine();

            Utils.PrintTable(costs);
            Console.WriteLine();
            Console.WriteLine($"{Utils.Factorial(numWorkers)} Possibilities");

            if (costGrahpPoints is not null)
            {
                /*double totalCosts = solver.Objective().Value();
                costGrahpPoints.Add(new GraphPoint(new Vector2D(numWorkers, 0), ConsoleColor.DarkBlue, "Minimal Costs"));
                costGrahpPoints.Add(new GraphPoint(new Vector2D(numWorkers, totalCosts), ConsoleColor.DarkBlue, $"{totalCosts:0.##}"));*/
                Console.WriteLine();
                Utils.DrawChart(costGrahpPoints, false);
            }

            Console.WriteLine();
            Console.WriteLine("Assignment Table:");
            Console.WriteLine();
            Utils.PrintTable(assignmentTable);
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