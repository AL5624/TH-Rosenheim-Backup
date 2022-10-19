package de.thro.inf.prg3.a03;

public class PlayfulState extends State {

    public PlayfulState(int time) {
        super(time);
    }

    @Override
    public State successor(Cat cat) {
        if (this.getTime() >= cat.getAwake()) {
            logger.info("Yoan... getting tired!");
            return new SleepingState();
        }

        return this;
    }
}
