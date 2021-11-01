package de.thro.inf.prg3.a03;

public class DigestingState extends State
{
    @Override
    public State successor(Cat cat)
    {
        if (time == cat.getDigest()) {
            logger.info("Getting in a playful mood!");
            return new PlayfulState();
        }

        return this;
    }
}
