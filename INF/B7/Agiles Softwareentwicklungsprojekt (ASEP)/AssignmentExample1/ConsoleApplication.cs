namespace ExampleOptimizers;
internal class ConsoleApplication
{
    private bool _exit = false;
    private State _state = State.PREPARATION;

    private void Start()
    {
        while (!_exit)
        {
            ReadCommand();
        }
    }

    private void ReadCommand()
    {
        string? input = Console.ReadLine();
        if (input == null)
            return;

        if (_state == State.PREPARATION)
        {
            Preparation();
        }
        else if (_state == State.AFTER_ROUND)
        {
            AfterRound();
        }

        string[] parameters = input.Split(' ');
        if (parameters.Length == 0)
            return;

        switch (parameters[ 0 ])
        {
            case "add":
            if (parameters.Length < 2)
                return;
            switch (parameters[ 1 ])
            {
                case "worker":
                AddWorker();
                break;
                case "task":
                AddTask();
                break;
            }

            break;
            case "exit":
            _exit = true;
            break;
        }
    }

    private void Preparation()
    {

    }

    private void AfterRound()
    {

    }

    public void AddWorker()
    {

    }

    public void AddTask()
    {

    }
}
