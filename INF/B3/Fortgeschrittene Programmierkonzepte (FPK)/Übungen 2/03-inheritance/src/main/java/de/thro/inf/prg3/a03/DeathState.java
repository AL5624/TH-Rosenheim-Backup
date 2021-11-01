package de.thro.inf.prg3.a03;

public class DeathState extends State
{
    @Override
    public State successor(Cat cat)
    {
        return this;
    }
}
