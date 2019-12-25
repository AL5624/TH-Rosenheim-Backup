package de.thro.inf.prg3.a06.tests;

import com.google.gson.Gson;
import de.thro.inf.prg3.a06.model.Joke;
import de.thro.inf.prg3.a06.model.JokeResponse;
import org.junit.Test;
import static org.junit.Assert.*;

public class GsonTest
{

	@Test
	public void GsonTest()
	{
		Joke joke = new Joke(10, "Hallo");
		Gson gson = new Gson();

		JokeResponse jokeResponse = new JokeResponse("success", joke);

		String jokeresponsejson = gson.toJson(jokeResponse);
		assertEquals("toJasonResponse", "{\"type\":\"success\",\"value\":{\"num\":10,\"ct\":\"Hallo\"}}", jokeresponsejson);

		JokeResponse jokeResponse2 = gson.fromJson(jokeresponsejson, JokeResponse.class);
		assertEquals("fromJsonResponse", jokeResponse.equals(jokeResponse2), true);


		String json = gson.toJson(joke);
		assertEquals("toJason", "{\"num\":10,\"ct\":\"Hallo\"}", json);

		Joke joke2 = gson.fromJson(json, Joke.class);
		assertEquals("fromJason", joke.equals(joke2), true);

		Joke joke3 = gson.fromJson("{'num': 10, 'ct': 'Hallo'}", Joke.class);
		assertEquals("fromJson2", joke.equals(joke3), true);
	}
}
