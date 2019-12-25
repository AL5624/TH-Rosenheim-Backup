package de.thro.inf.prg3.a03;

public class SleepingState extends  State
{
    public SleepingState (int d)
    {
        super(d);
    }

    @Override
    public State successor (Cat cat)
    {
        if (t < d) return this;
        return new HungryState(d);
    }
}
