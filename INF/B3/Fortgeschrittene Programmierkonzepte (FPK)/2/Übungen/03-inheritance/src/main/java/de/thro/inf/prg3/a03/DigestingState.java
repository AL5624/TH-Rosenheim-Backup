package de.thro.inf.prg3.a03;

public class DigestingState extends State {
    private int startTime = 0;

    public DigestingState(int time) {
        super(time);
        this.startTime = time;
    }

    @Override
    public State successor(Cat cat) {
        if (this.getTime() - this.startTime == cat.getDigest()) {
            logger.info("Getting in a playful mood!");
            return new PlayfulState(this.getTime());
        }

        return this;
    }
}
