package de.thro.inf.prg3.a03;

public class SleepingState extends State
{
    public SleepingState(int time) {
        super(time);
    }

    @Override
    public State successor(Cat cat)
    {
        if (time == cat.getSleep()) {
            logger.info("Yoan... getting hungry!");
            time = 0;
            return new HungryState(time);
        }

        return this;
    }
}
