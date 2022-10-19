package de.thro.inf.prg3.a03;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class State {
    protected static final Logger logger = LogManager.getLogger();
    @Getter
    private int time = 0;


    protected State() {}

    protected State(int duration) {
        this.time = duration;
    }


    public final State tick(Cat cat) {
        ++time;
        return this.successor(cat);
    }

    public abstract State successor(Cat cat);
}
