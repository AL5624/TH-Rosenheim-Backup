package de.thro.inf.prg3.a03;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class Cat {
	private static final Logger logger = LogManager.getLogger();

	private State currentState = new SleepingState();

	// state durations (set via constructor), ie. the number of ticks in each state
	private final int sleep;
	private final int awake;
	private final int digest;

	private final String name;

	public Cat(String name, int sleep, int awake, int digest) {
		this.name = name;
		this.sleep = sleep;
		this.awake = awake;
		this.digest = digest;
	}

	public void tick(){
		logger.info("tick()");
		this.currentState = this.currentState.tick(this);
	}

	/**
	 * This would be a user interaction: feed the cat to change its state!
	 */
	public void feed(){
		if (!isHungry())
			throw new IllegalStateException("Can't stuff a cat...");

		logger.info("You feed the cat...");
		this.currentState = ((HungryState) this.currentState).feed();
	}

	private boolean isInState(Class<?> state) {
		return this.currentState.getClass().equals(state);
	}

	public boolean isAsleep() {
		return this.isInState(SleepingState.class);
	}

	public boolean isPlayful() {
		return this.isInState(PlayfulState.class);
	}

	public boolean isHungry() {
		return this.isInState(HungryState.class);
	}

	public boolean isDigesting() {
		return this.isInState(DigestingState.class);
	}

	public boolean isDead() {
		return this.isInState(DeadState.class);
	}

	@Override
	public String toString() {
		return name;
	}

}
