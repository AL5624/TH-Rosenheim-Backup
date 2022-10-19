package de.thro.inf.prg3.a03;

public class HungryState extends State
{
    public HungryState(int d)
    {
        super(d);
    }

    @Override
    State successor(Cat cat)
    {
        return new DigestingState(d);
    }
}
