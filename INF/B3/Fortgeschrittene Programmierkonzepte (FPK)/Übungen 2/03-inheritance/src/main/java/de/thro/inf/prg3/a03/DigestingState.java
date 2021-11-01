package de.thro.inf.prg3.a03;

public class DigestingState extends State
{
    public DigestingState(int time) {
        super(time);
    }

    @Override
    public State successor(Cat cat)
    {
        if (++duration == cat.getDigest()) {
            logger.info("Getting in a playful mood!");
            return new PlayfulState(time);
        }

        return this;
    }
}
