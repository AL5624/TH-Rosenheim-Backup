package de.thro.inf.prg3.a03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static de.thro.inf.prg3.a03.Cat.State.*;

public class Cat {
	private static final Logger logger = LogManager.getLogger();

	// valid states
	public enum State {SLEEPING, HUNGRY, DIGESTING, PLAYFUL, DEAD}

	// initially, animals are sleeping
	private State state = State.SLEEPING;

	public de.thro.inf.prg3.a03.State getCurrentState()
	{
		return currentState;
	}

	private de.thro.inf.prg3.a03.State currentState = new SleepingState(0);

	// state durations (set via constructor), ie. the number of ticks in each state
	private final int sleep;
	private final int awake;
	private final int digest;

	private final String name;

	public int getSleep()
	{
		return sleep;
	}

	public int getAwake()
	{
		return awake;
	}

	public int getDigest()
	{
		return digest;
	}

	public String getName()
	{
		return name;
	}

	private int time = 0;
	private int timeDigesting = 0;

	public Cat(String name, int sleep, int awake, int digest) {
		this.name = name;
		this.sleep = sleep;
		this.awake = awake;
		this.digest = digest;
	}

	private static Map<State, StateInterface> stateMap = new HashMap<>();

	static {
		stateMap.put(SLEEPING, cat ->
		{
			if (cat.time == cat.sleep) {
				logger.info("Yoan... getting hungry!");
				cat.state = HUNGRY;
				cat.time = 0;
			}
		});

		stateMap.put(HUNGRY, cat ->
		{
			if(cat.time == cat.awake){
				logger.info("I've starved for a too long time...good bye...");
				cat.state = DEAD;
			}
		});

		stateMap.put(DIGESTING, cat ->
		{
			cat.timeDigesting = cat.timeDigesting + 1;
			if (cat.timeDigesting == cat.digest) {
				logger.info("Getting in a playful mood!");
				cat.state = PLAYFUL;
			}
		});

		stateMap.put(PLAYFUL, cat ->
		{
			if (cat.time >= cat.awake) {
				logger.info("Yoan... getting tired!");
				cat.state = SLEEPING;
				cat.time = 0;
			}
		});

		stateMap.put(DEAD, cat ->
		{
		});
	}

	public void tick(){
		logger.info("tick()");
		// time = time + 1;
		// stateMap.get(state).updateState(this);
		// logger.info(state);
		currentState = currentState.tick(this);
		logger.info(currentState);
	}

	/**
	 * This would be a user interaction: feed the cat to change its state!
	 */
	public void feed(){
		if (!isHungry())
			throw new IllegalStateException("Can't stuff a cat...");

		logger.info("You feed the cat...");

		// change state and reset the timer
		// state = State.DIGESTING;
		// timeDigesting = 0;
		currentState = ((HungryState) currentState).feed(this);
	}

	public boolean isAsleep() {
		return currentState instanceof SleepingState;
		// return state.equals(State.SLEEPING);
	}

	public boolean isPlayful() {
		return currentState instanceof PlayfulState;
		// return state.equals(State.PLAYFUL);
	}

	public boolean isHungry() {
		return currentState instanceof HungryState;
		// return state.equals(State.HUNGRY);
	}

	public boolean isDigesting() {
		return currentState instanceof DigestingState;
		// return state.equals(State.DIGESTING);
	}

	public boolean isDead() {
		return currentState instanceof  DeathState;
		// return state == State.DEAD;
	}

	@Override
	public String toString() {
		return name;
	}

}
