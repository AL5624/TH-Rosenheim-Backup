package de.thro.inf.prg3.a06.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Peter Kurfer
 * Created on 11/9/17.
 */
public final class Joke {
	@SerializedName("id")
	private int number;
	@SerializedName("joke")
	private String content;
	@SerializedName("categories")
	private String[] rubrics;

	public Joke (int number, String content)
	{
		this.number = number;
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getRubrics()
	{
		return rubrics[0];
	}

	public void setRubrics(String[] rubrics)
	{
		this.rubrics = rubrics;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof Joke)) return false;

		Joke joke1 = (Joke) o;

		return new EqualsBuilder()
				.append(getNumber(), joke1.getNumber())
				.append(getContent(), joke1.getContent())
				.append(rubrics, joke1.rubrics)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(getNumber())
				.append(getContent())
				.append(rubrics)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("number", number)
				.append("content", content)
				.append("rubrics", rubrics)
				.toString();
	}
/*
	public static void main(String[] args)
	{
		Joke joke = new Joke(10, "Hallo");
		Gson gson = new Gson();
		String json = gson.toJson(joke);
		Joke joke2 = new Gson().fromJson(json, Joke.class);
		Joke joke3 = gson.fromJson("{'number': 23, 'content': 'Hallo'}", Joke.class);
	}*/
}
