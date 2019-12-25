package de.thro.inf.prg3.a06.model;

public class JokeResponse
{
	private String type;

	public String getType()
	{
		return type;
	}

	public Joke getValue()
	{
		return value;
	}

	private Joke value;

	public JokeResponse(String type, Joke value)
	{
		this.type = type;
		this.value = value;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;

		if (!(o instanceof JokeResponse)) return false;

		JokeResponse tmp = (JokeResponse) o;

		if(tmp.type.equals(this.type) && tmp.value.equals(this.value)) return true;

		return false;

	}
}
