using Google.Protobuf.Collections;

namespace ExampleOptimizers;
internal class Utils
{
    private static int tableWidth = 73;

    public static void PrintTable(double[,] table, string[]? header = null, string label = "Worker", int tableWidth = 100)
    {
        Console.ResetColor();
        Utils.tableWidth = tableWidth;
        string[,] data = new string[ table.GetLength(0), table.GetLength(1) ];
        for (int i = 0; i < table.GetLength(0); i++)
        {
            for (int j = 0; j < table.GetLength(1); j++)
            {
                double value = table[i, j];
                data[ i, j ] = value.ToString("0.##");
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
            PrintHeader(table.GetLength(1));
        }

        PrintLine();
        for (int i = 0; i < data.GetLength(0); i++)
        {
            string[] row = new string[data.GetLength(1) + 1];
            FillArray(row, (index) => index == 0 ? $"{label} {i}" : data[ i, index - 1 ]);
            PrintRow(row);
            PrintLine();
        }
    }

    private static void PrintHeader(int numHeaders)
    {
        string[] headers = new string[numHeaders + 1];

        FillArray(headers, (index) => index == 0 ? "" : $"Task {index - 1}");

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

    public static void FillArray<T>(T[] array, Func<int, T> callback)
    {
        for (int i = 0; i < array.Length; i++)
        {
            array[ i ] = callback(i);
        }
    }

    public static void FillArray2D<T>(T[,] array, Func<int, T[]> callback)
    {
        for (int i = 0; i < array.GetLength(0); i++)
        {
            T[] innner = callback(i);
            for (int j = 0; j < innner.Length && j < array.GetLength(1); j++)
            {
                array[ i, j ] = innner[ j ];
            }
        }
    }

    public static void DrawChart(List<GraphPoint> dict, bool showCoordinates = true)
    {
        Console.ResetColor();
        Console.WriteLine();
        int consoleWidth = 100;
        int consoleHeight = 20;
        int actualConsoleHeight = consoleHeight * 2;
        double minX = dict.Min(c => c.Position.X);
        double minY = dict.Min(c => c.Position.Y);
        double maxX = dict.Max(c => c.Position.X);
        double maxY = dict.Max(c => c.Position.Y);

        Console.WriteLine(maxY.ToString("0.##"));
        // normalize points to new coordinates
        dict.ForEach(p =>
        {
            p.Normalized = new Vector2D(p.Position.X - minX, p.Position.Y - minY);
        });

        dict.ForEach(p =>
        {
            p.Normalized = new Vector2D(
                    (int) Math.Round((float) p.Normalized.X / (maxX - minX) * (consoleWidth - 1)),
                    (int) Math.Round((float) p.Normalized.Y / (maxY - minY) * (actualConsoleHeight - 1))
                );
        });

        GraphPoint[] normalized = dict.ToArray();
        GraphPoint[] IsHit(int hx, int hy)
        {
            return Array.FindAll(normalized, p => p.Normalized.X == hx && p.Normalized.Y == hy);
        }

        MapField<string, GraphPoint> cache = new();

        for (int y = actualConsoleHeight - 1; y > 0; y -= 2)
        {
            Console.Write(y == actualConsoleHeight - 1 ? '┌' : '│');

            for (int x = 0; x < consoleWidth; x++)
            {
                string getKey(int x, int y)
                {
                    return x.ToString() + "-" + y.ToString();
                }

                GraphPoint[] hitTop = IsHit(x, y);
                GraphPoint[] hitBottom = IsHit(x, y - 1);

                GraphPoint point;
                string label = "";
                string tag = "";
                string coordinates = "" ;

                if (hitTop.Length > 0)
                {
                    point = hitTop[ 0 ];
                    Console.ForegroundColor = point.Color;
                    Console.BackgroundColor = ConsoleColor.Black;
                    Console.Write('▀');
                    label = point.Label != "" ? (point.Label + " ") : "";
                    coordinates = showCoordinates ? $"({point.Position.X:0.##}, {point.Position.Y:0.##})" : "";
                    tag = label + coordinates;
                    Console.Write(tag);

                    if (hitTop.Length > 1)
                    {
                        for (int i = 1; i < hitTop.Length; i++)
                        {
                            point = hitTop[ i ];
                            int m = 2;
                            int Y()
                            {
                                return y - (i * m);
                            }

                            string key = getKey(x, Y());
                            while (cache.ContainsKey(key))
                            {
                                m += 2;
                                key = getKey(x, Y());
                            }

                            cache.Add(key, point);
                        }
                    }

                    x += tag.Length;
                }

                if (hitBottom.Length > 0)
                {
                    if (hitTop.Length < 1)
                    {
                        point = hitBottom[ 0 ];
                        Console.ForegroundColor = point.Color;
                        Console.BackgroundColor = ConsoleColor.Black;
                        Console.Write('▀');
                        label = point.Label != "" ? (point.Label + " ") : "";
                        coordinates = showCoordinates ? $"({point.Position.X:0.##}, {point.Position.Y:0.##})" : "";
                        tag = label + coordinates;
                        Console.Write(tag);
                        x += tag.Length;
                    }
                    else
                    {
                        point = hitBottom[ 0 ];
                        int m = 2;
                        int X = x - tag.Length;
                        int Y()
                        {
                            return y - (1 * m);
                        }

                        string key = getKey(X, Y());
                        while (cache.ContainsKey(key))
                        {
                            m += 2;
                            key = getKey(X, Y());
                        }

                        cache.Add(key, point);
                    }

                    if (hitBottom.Length > 1)
                    {
                        for (int i = 1; i < hitBottom.Length; i++)
                        {
                            point = hitBottom[ i ];
                            int m = 2;
                            int X = x - tag.Length;
                            int Y()
                            {
                                return y - ((i + (hitTop.Length < 1 ? 0 : 1)) * m);
                            }

                            string key = getKey(X, Y());
                            while (cache.ContainsKey(key))
                            {
                                m += 2;
                                key = getKey(X, Y());
                            }

                            cache.Add(key, point);
                        }
                    }
                }
                else if (cache.ContainsKey(getKey(x, y)))
                {
                    point = cache[ getKey(x, y) ];
                    Console.ForegroundColor = point.Color;
                    Console.BackgroundColor = ConsoleColor.Black;
                    Console.Write(' ');
                    label = point.Label != "" ? (point.Label + " ") : "";
                    coordinates = showCoordinates ? $"({point.Position.X:0.##}, {point.Position.Y:0.##})" : "";
                    tag = label + coordinates;
                    Console.Write(tag);
                    x += tag.Length;
                    cache.Remove(getKey(x, y));
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
        Console.Write((dict.Min(x => x.Position.Y) + "/" + dict.Min(x => x.Position.X)).PadRight(consoleWidth / 3));
        Console.Write((dict.Max(x => x.Position.X) / 2).ToString("0.##").PadLeft(consoleWidth / 3 / 2).PadRight(consoleWidth / 3));
        Console.WriteLine(dict.Max(x => x.Position.X).ToString("0.##").PadLeft(consoleWidth / 3));
        Console.WriteLine();
    }

    public static int Factorial(int num)
    {
        int result = num;
        for (int i = result - 1; i > 0; i--)
        {
            result *= i;
        }

        return result;
    }

    public static int positivMod(int x, int m)
    {
        int r = x%m;
        return r < 0 ? r + m : r;
    }

    public static int RandomInt(int lb, int ub)
    {
        return new Random().Next(lb, ub);
    }
}
