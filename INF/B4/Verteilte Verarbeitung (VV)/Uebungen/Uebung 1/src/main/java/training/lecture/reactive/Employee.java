package training.lecture.reactive;

public class Employee
{
    //Zustandsmenge
    enum State{ABSENT, PRESENT, PASSAGE, ERROR}
    //Eingabealphabet, Eingabesymbole
    enum Symbol{LEFT, RIGHT}

    //Zustandsübergangsfunktion
        //      ABSENT,         PRESENT,    PASSAGE,        ERROR
    private State[][] transitionTable = {
                {State.PASSAGE, State.ERROR, State.ABSENT, State.ERROR}, //LEFT
                {State.ERROR, State.PASSAGE, State.PRESENT, State.ERROR }//RIGHT
    };

    public void transition(Symbol symbol)
    {
        currentState = transitionTable[symbol.ordinal()][currentState.ordinal()];
    }

    //Startzustand / Aktuellen Zustand

    private State currentState = State.ABSENT;

    public Employee()
    {
        currentState = State.ABSENT;
    }

    public State getCurrentState()
    {
        return currentState;
    }

}
