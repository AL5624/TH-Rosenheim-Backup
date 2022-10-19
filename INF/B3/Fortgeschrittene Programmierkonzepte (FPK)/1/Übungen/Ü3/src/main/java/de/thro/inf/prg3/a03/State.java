package de.thro.inf.prg3.a03;

public abstract class State
{
    int t = 0;
    int d;

    public State (int d)
    {
        this.d = d;
    }

    public State tick(Cat cat)
    {
       ++t;
       return successor(cat);
    }

    abstract State successor(Cat cat);
}
