namespace AssignmentExample1;
internal class Utils
{
    private static int tableWidth = 73;

    public static void PrintTable(double[,] table, string[]? header = null, string label = "Worker", int tableWidth = 100)
    {
        Utils.tableWidth = tableWidth;
        string[,] data = new string[table.GetLength(0), table.GetLength(1)];
        for (int i = 0; i < table.GetLength(0); i++)

        {
            for (int j = 0; j < table.GetLength(1); j++)
            {
                double value = table[i, j];
                data[ i, j ] = value == 0 ? value.ToString() : value.ToString("#.##");
            }
        }

        // Console.Clear();
        PrintLine();
        if (header is not null)
        {
            PrintRow(header);
        }
        else
        {
            PrintHeader(table.GetLength(0));
        }

        PrintLine();
        for (int i = 0; i < data.GetLength(0); i++)
        {
            string[] row = new string[data.GetLength(1) + 1];
            InitArray(row, (index) => index == 0 ? $"{label} {i}" : data[ i, index - 1 ]);
            PrintRow(row);
            PrintLine();
        }
    }

    private static void PrintHeader(int numHeaders)
    {
        string[] headers = new string[numHeaders + 1];

        InitArray(headers, (index) => index == 0 ? "" : $"Task {index - 1}");

        PrintRow(headers);
    }

    private static void PrintLine()
    {
        Console.WriteLine(new string('-', tableWidth));
    }

    private static void PrintRow(params string[] columns)
    {
        int width = (tableWidth - columns.Length) / columns.Length;
        string row = "|";

        foreach (string column in columns)
        {
            row += AlignCentre(column, width) + "|";
        }

        Console.WriteLine(row);
    }

    private static string AlignCentre(string text, int width)
    {
        text = text.Length > width ? text[ ..(width - 3) ] + "..." : text;

        if (string.IsNullOrEmpty(text))
        {
            return new string(' ', width);
        }
        else
        {
            return text.PadRight(width - ((width - text.Length) / 2)).PadLeft(width);
        }
    }

    public static void InitArray<T>(T[] array, Func<int, T> callback)
    {
        for (int i = 0; i < array.Length; i++)
        {
            array[ i ] = callback(i);
        }
    }

    public static void DrawChart(List<Node> dict)
    {
        Console.WriteLine();
        int consoleWidth = 78;
        int consoleHeight = 20;
        int actualConsoleHeight = consoleHeight * 2;
        int minX = dict.Min(c => c.Position.X);
        int minY = dict.Min(c => c.Position.Y);
        int maxX = dict.Max(c => c.Position.X);
        int maxY = dict.Max(c => c.Position.Y);

        Console.WriteLine(maxX);
        // normalize points to new coordinates
        Node[][] normalized = dict.
            Select(c => new Node[] { new Node(new(c.Position.X - minX, c.Position.Y - minY), c.ConsoleColor), c}).
            Select(c =>
            {
                return new Node[]
                    {
                        new Node(
                        new(
                            (int)Math.Round((float) c[0].Position.X / (maxX - minX) * (consoleWidth - 1)),
                            (int)Math.Round((float) c[0].Position.Y / (maxY - minY) * (actualConsoleHeight - 1))
                           ),
                        c[0].ConsoleColor),
                        c[1]
                    };
            }).ToArray();
        Node? node = null;
        bool IsHit(int hx, int hy)
        {
            return normalized.Any(c =>
            {
                if (c[ 0 ].Position.X == hx && c[ 0 ].Position.Y == hy)
                {
                    node = c[ 1 ];
                    return true;
                }

                return false;
            });
        }

        int taskNum = 0;
        int workerNum = 0;

        for (int y = actualConsoleHeight - 1; y > 0; y -= 2)
        {
            Console.Write(y == actualConsoleHeight - 1 ? '┌' : '│');
            for (int x = 0; x < consoleWidth; x++)
            {
                bool hitTop = IsHit(x, y);
                bool hitBottom = IsHit(x, y - 1);
                ConsoleColor color = ConsoleColor.Red;
                bool isTask = false;
                int posX = x;
                int posY = y;

                if (node is not null)
                {
                    color = node.ConsoleColor;
                    isTask = node.ConsoleColor == ConsoleColor.Red;
                    taskNum = 0;
                    workerNum = 0;
                    for (int i = 0; i < dict.Count(); i++)
                    {
                        Node d = dict.ElementAt(i);
                        if (isTask && d.ConsoleColor == ConsoleColor.Red)
                        {
                            ++taskNum;
                        }
                        else if (!isTask && d.ConsoleColor == ConsoleColor.Blue)
                        {
                            ++workerNum;
                        }

                        if (d.Position.X == node.Position.X && d.Position.Y == node.Position.Y)
                        {
                            --taskNum;
                            --workerNum;
                            break;
                        }
                    }

                    posX = node.Position.X;
                    posY = node.Position.Y;
                }

                if (hitBottom && hitTop)
                {
                    Console.ForegroundColor = color;
                    Console.BackgroundColor = ConsoleColor.Black;
                    Console.Write('█');
                    Console.Write((isTask ? $"T{taskNum++}" : $"W{workerNum++}") + $"({posX}, {posY})");
                }
                else if (hitTop)
                {
                    Console.ForegroundColor = color;
                    Console.BackgroundColor = ConsoleColor.Black;
                    Console.Write('▀');
                    Console.Write((isTask ? $"T{taskNum++}" : $"W{workerNum++}") + $"({posX}, {posY})");
                }
                else if (hitBottom)
                {
                    Console.ForegroundColor = color;
                    Console.BackgroundColor = ConsoleColor.Black;
                    Console.Write('▀');
                    Console.Write((isTask ? $"T{taskNum++}" : $"W{workerNum++}") + $"({posX}, {posY})");
                }
                else
                {
                    Console.ForegroundColor = ConsoleColor.Black;
                    Console.BackgroundColor = ConsoleColor.Black;
                    Console.Write('▀');
                }
            }

            Console.ResetColor();
            Console.WriteLine();
        }

        Console.WriteLine('└' + new string('─', (consoleWidth / 2) - 1) + '┴' + new string('─', (consoleWidth / 2) - 1) + '┘');
        Console.Write((dict.Min(x => x.Position.X) + "/" + dict.Min(x => x.Position.Y)).PadRight(consoleWidth / 3));
        Console.Write((dict.Max(x => x.Position.Y) / 2).ToString().PadLeft(consoleWidth / 3 / 2).PadRight(consoleWidth / 3));
        Console.WriteLine(dict.Max(x => x.Position.Y).ToString().PadLeft(consoleWidth / 3));
        Console.WriteLine();
    }
}
