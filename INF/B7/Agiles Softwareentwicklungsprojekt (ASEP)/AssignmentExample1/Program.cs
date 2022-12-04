using Google.OrTools.LinearSolver;
using Google.Protobuf.Collections;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace ExampleOptimizers;

internal class Program
{
    private static bool random = false;
    private static void Main(string[] args)
    {
        int numWorker = Utils.RandomInt(1, 8);
        int numTasks = Utils.RandomInt(1, 8);
        RandomTest(5, 7);
    }

    private static void RandomTest(int numWorkers = 7, int numTasks = 7)
    {
        random = true;
        Node[] nodes = new Node[ numWorkers + numTasks];
        Utils.FillArray(nodes, (i) =>
        {
            return new Node(Vector2D.RandomPosition(0, numWorkers + numTasks));
        });

        Worker[] workers = new Worker[ numWorkers ];
        for (int i = 0; i < numWorkers; i++)
        {
            workers[ i ] = new Worker(nodes[ i ]);
        }

        Task[] tasks = new Task[ numTasks ];
        for (int i = numWorkers; i < numWorkers + numTasks; i++)
        {
            tasks[ i - numWorkers ] = new Task(nodes[ i ], "Task " + (i - numWorkers));
        }

        TEST(workers, tasks);
    }

    private static void DistanceTasks()
    {
        random = false;
        Node[] nodes = new Node[6];
        nodes[ 0 ] = new(new(1, 1));
        nodes[ 1 ] = new(new(1, 3));
        nodes[ 2 ] = new(new(1, 5));
        nodes[ 3 ] = new(new(10, 10));
        nodes[ 4 ] = new(new(2, 2));
        nodes[ 5 ] = new(new(2, 4));


        Task[] tasks = new Task[4];
        tasks[ 0 ] = new Task(nodes[ 0 ], "Task 0");
        tasks[ 1 ] = new Task(nodes[ 1 ], "Task 1");
        tasks[ 2 ] = new Task(nodes[ 2 ], "Task 2");
        tasks[ 3 ] = new Task(nodes[ 3 ], "Task 3");

        Worker[] workers = new Worker[2];
        workers[ 0 ] = new Worker(nodes[ 4 ]);
        workers[ 1 ] = new Worker(nodes[ 5 ]);

        TEST(workers, tasks);
    }

    private static void TEST(Worker[] workers, Task[] tasks)
    {
        if (IsPrintable(workers.Length, tasks.Length))
        {
            PrintPositionTable(workers, tasks);
        }

        int createdTasks = 3;

        for (int i = 0; i < 10; i++)
        {
            List<Worker> workerList = Utils.ArrayToList(workers);
            List<Task> taskList = Utils.ArrayToList(tasks);
            SortedDictionary<int, List<Task>> priorityDict = GetPriorityList(taskList);

            Console.WriteLine("\nCurrent Round: " + (i + 1));

            Console.WriteLine("\nAvailable Tasks:");
            taskList.ForEach(task =>
            {
                Console.WriteLine(task.Name);
            });

            Console.WriteLine("\nNumber of Priority Dictionary Lists: " + priorityDict.Count);

            for (int p = priorityDict.Count - 1; p > -1; p--)
            {
                Console.WriteLine("\nSelected Priority: " + (priorityDict.ElementAt(p).Key));
                List<Task> currentTaskList = priorityDict.ElementAt(p).Value;
                Console.WriteLine("\nCurrent selected Tasks:");
                currentTaskList.ForEach(task =>
                {
                    Console.WriteLine(task.Name + ", Priority: " + task.Priority + ", Future: " + task.future + ", Rounds not taken: " + task.notTakenRounds);
                });

                List<Assignment> assignments = Solve(workerList, currentTaskList);

                assignments.ForEach(assignment =>
                {
                    assignment.workers.ForEach(worker =>
                    {
                        workerList.Remove(worker);
                    });

                    currentTaskList.Remove(assignment.task);
                    int index = taskList.FindIndex(0, taskList.Count, (t) => t == assignment.task);
                    if (index > -1)
                    {
                        tasks[ index ] = new Task(random ? new Node(Vector2D.RandomPosition(0, workerList.Count + taskList.Count)) : assignment.task.Node, $"Task {++createdTasks}");
                    }
                });

                currentTaskList.ForEach(task =>
                {
                    task.NotTaken();
                });

                if (workerList.Count < 1)
                {
                    for (int m = 0; m < p; m++)
                    {
                        priorityDict.ElementAt(m).Value.ForEach(task =>
                        {
                            task.NotTaken();
                        });
                    }

                    p = -1;
                }
            }
        }
    }

    private static SortedDictionary<int, List<Task>> GetPriorityList(List<Task> taskList)
    {
        SortedDictionary<int, List<Task>> priorityList = new();

        taskList.ForEach(task =>
        {
            if (!priorityList.ContainsKey(task.Priority))
            {
                priorityList.Add(task.Priority, new());
            }

            List<Task> ?priority = new();

            priorityList.TryGetValue(task.Priority, out priority);

            if (priority is not null)
            {
                priority.Add(task);
            }
        });

        return priorityList;
    }

    private static bool IsPrintable(int numWorkers, int numTasks)
    {
        return numWorkers < 8 && numTasks < 8;
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

    private static double[,] GetCostTable(List<Worker> workers, List<Task> tasks)
    {
        double[,] costs = new double[ workers.Count, tasks.Count ];

        for (int workerIndex = 0; workerIndex < workers.Count; workerIndex++)
        {
            Worker worker = workers.ElementAt(workerIndex);
            for (int taskIndex = 0; taskIndex < tasks.Count; taskIndex++)
            {
                Task task = tasks.ElementAt(taskIndex);
                costs[ workerIndex, taskIndex ] = Vector2D.Distance(worker.Node.Position, task.Node.Position);
            }
        }

        return costs;
    }

    private static List<Assignment> Solve(List<Worker> workerList, List<Task> taskList)
    {
        int minimalRequiredWorkers = Enumerable.Range(1, taskList.Count).Aggregate(0, (acc, i) => acc + (int) taskList.ElementAt(i - 1).GetMinimalRequiredWorkers());

        double[,] costs = GetCostTable(workerList, taskList);

        Console.WriteLine("\nminimalRequiredWorkers: " + minimalRequiredWorkers);

        int availableWorkers = workerList.Count;

        Console.WriteLine("\navailableWorkers: " + availableWorkers);

        int numTasks = costs.GetLength(1);

        Solver solver = Solver.CreateSolver("SCIP");
        if (solver is null)
        {
            throw new Exception();
        }

        // x[i, j] is an array of 0-1 variables, which will be 1
        // if worker i is assigned to task j.
        Variable[,] assignments = new Variable[availableWorkers, numTasks];
        for (int i = 0; i < availableWorkers; ++i)
        {
            for (int j = 0; j < numTasks; ++j)
            {
                assignments[ i, j ] = solver.MakeIntVar(0, 1, $"worker_{i}_task_{j}");
            }
        }

        // Each worker is assigned to at most one task if the number of tasks is smaller than the number of worker
        // Each worker is assigned to exactly one task if the number of tasks is more or equal than the number of worker
        for (int i = 0; i < availableWorkers; ++i)
        {
            Worker worker = workerList.ElementAt(i);
            Constraint constraint = solver.MakeConstraint(minimalRequiredWorkers >= availableWorkers ? 1 : 0, 1, $"c-w-{i}");

            for (int j = 0; j < numTasks; ++j)
            {
                constraint.SetCoefficient(assignments[ i, j ], 1);
            }
        }
        Console.WriteLine();
        Console.WriteLine();
        // Each task is assigned to exactly one worker if the number of worker is equal or more than the number of tasks
        // Each task is assigned to at most one worker if the number of worker is smaller than the number of tasks
        for (int j = 0; j < numTasks; ++j)
        {
            Task task = taskList.ElementAt(j);
            Constraint constraint = solver.MakeConstraint(minimalRequiredWorkers > availableWorkers ? 0 : 1, 1, $"c-t-{j}");

            for (int i = 0; i < availableWorkers; ++i)
            {
                Worker worker = workerList.ElementAt(i);
                constraint.SetCoefficient(assignments[ i, j ], 1);
            }
        }

        Objective objective = solver.Objective();
        for (int i = 0; i < availableWorkers; ++i)
        {
            Worker worker = workerList.ElementAt(i);
            for (int j = 0; j < numTasks; ++j)
            {
                Task task = taskList.ElementAt(j);

                costs[ i, j ] = worker.AlterCosts(task, costs[ i, j ]);
                costs[ i, j ] = task.AlterCosts(costs[ i, j ]);

                objective.SetCoefficient(assignments[ i, j ], costs[ i, j ] );
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
                    return value;
                });

                return tmp;
            });

            Console.WriteLine();
            Console.WriteLine("Costs Table:");
            Console.WriteLine();

            if (IsPrintable(availableWorkers, numTasks))
            {
                string[] header = new string[ taskList.Count + 1 ];
                header[ 0 ] = "";
                for (int i = 0; i < taskList.Count; i++)
                {
                    header[ i + 1 ] = taskList.ElementAt(i).Name;
                }
                Utils.PrintTable(costs, header);
            }
            Console.WriteLine();
            Console.WriteLine("Assignment Table:");
            Console.WriteLine();
            if (IsPrintable(availableWorkers, numTasks))
            {
                string[] header = new string[ taskList.Count + 1 ];
                header[ 0 ] = "";
                for (int i = 0; i < taskList.Count; i++)
                {
                    header[ i + 1 ] = taskList.ElementAt(i).Name;
                }
                Utils.PrintTable(assignmentTable, header);
            }

            Console.WriteLine();

            Console.WriteLine($"Total cost: {solver.Objective().Value():0.##}\n");
            List<Assignment> result = new();
            for (int i = 0; i < availableWorkers; ++i)
            {
                Worker worker = workerList.ElementAt(i);

                for (int j = 0; j < numTasks; ++j)
                {
                    // Test if x[i, j] is 0 or 1 (with tolerance for floating point
                    // arithmetic).
                    Task task = taskList.ElementAt(j);
                    if (IsAssigned(assignments[ i, j ].SolutionValue(), task))
                    {
                        int index = result.FindIndex(0, result.Count, (a) => a.task == task);
                        if (index > -1)
                        {
                            result.ElementAt(index).workers.Add(worker);
                        }
                        else
                        {
                            result.Add(new(worker, task, assignments[ i, j ], costs[ i, j ]));
                        }
                        
                        Console.WriteLine($"Worker {i} assigned to Task {j}. Cost: {costs[ i, j ]:0.##}");
                    }
                }
            }

            return result;
        }
        else
        {
            Console.WriteLine("No solution found.");
            throw new Exception();
        }
    }

    public static bool IsAssigned(double solotionValue, Task task)
    {
        return solotionValue > 0.5;// solotionValue - (1 / task.GetMinimalRequiredWorkers()) >= -0.0009;
    }
}