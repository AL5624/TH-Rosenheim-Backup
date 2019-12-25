package de.thro.inf.prg3.a03;

public class PlayfulState extends State
{
    public PlayfulState(int d)
    {
        super(d);
    }

    @Override
    State successor(Cat cat)
    {
        return new SleepingState(d);
    }
}
