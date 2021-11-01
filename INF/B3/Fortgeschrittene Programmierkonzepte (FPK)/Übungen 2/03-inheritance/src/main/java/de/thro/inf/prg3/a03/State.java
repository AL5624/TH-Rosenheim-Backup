package de.thro.inf.prg3.a03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class State
{
    protected static final Logger logger = LogManager.getLogger();
    protected int time = 0;
    protected int duration = 0;

    public State tick(Cat cat)
    {
        ++time;
        return cat.getCurrentState().successor(cat);
    }

    public abstract State successor(Cat cat);
}
