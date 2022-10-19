package de.thro.inf.prg3.a03;

public class HungryState extends State {

    @Override
    public State successor(Cat cat) {
        if (this.getTime() == cat.getAwake()) {
            logger.info("I've starved for a too long time...good bye...");
            return new DeadState();
        }

        return this;
    }

    public State feed() {
        return new DigestingState(this.getTime());
    }
}
