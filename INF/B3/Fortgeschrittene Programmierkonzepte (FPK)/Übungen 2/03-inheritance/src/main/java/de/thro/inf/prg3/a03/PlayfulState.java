package de.thro.inf.prg3.a03;

public class PlayfulState extends State
{
    @Override
    public State successor(Cat cat)
    {
        if (time >= cat.getAwake()) {
            logger.info("Yoan... getting tired!");
            return new SleepingState();
        }

        return this;
    }
}
