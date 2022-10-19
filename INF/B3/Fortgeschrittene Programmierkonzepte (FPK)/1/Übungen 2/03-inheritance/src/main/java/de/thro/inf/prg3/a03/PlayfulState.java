package de.thro.inf.prg3.a03;

public class PlayfulState extends State
{
    public PlayfulState(int time) {
        super(time);
    }

    @Override
    public State successor(Cat cat)
    {
        if (time >= cat.getAwake()) {
            logger.info("Yoan... getting tired!");
            time = 0;
            return new SleepingState(time);
        }

        return this;
    }
}
