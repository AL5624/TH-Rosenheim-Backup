package de.thro.inf.prg3.a03;

public class HungryState extends State
{
    public HungryState(int time) {
        super(time);
    }

    @Override
    public State successor(Cat cat)
    {
        if(time == cat.getAwake()){
            logger.info("I've starved for a too long time...good bye...");
            return new DeathState(time  );
        }

        return this;
    }

    public State feed(Cat cat) {
        return new DigestingState(time);
    }
}
