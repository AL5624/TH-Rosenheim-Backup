package de.thro.inf.prg3.a03;

public class DigestingState extends State
{
    public DigestingState(int d)
    {
        super(d);
    }

    @Override
    State successor(Cat cat)
    {
        return new PlayfulState(d);
    }
}
