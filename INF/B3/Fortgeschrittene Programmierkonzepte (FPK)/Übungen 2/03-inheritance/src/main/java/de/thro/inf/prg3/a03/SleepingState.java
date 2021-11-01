package de.thro.inf.prg3.a03;

public class SleepingState extends State
{
    @Override
    public State successor(Cat cat)
    {
        if (time == cat.getSleep()) {
            logger.info("Yoan... getting hungry!");
            return new HungryState();
        }

        return this;
    }
}
