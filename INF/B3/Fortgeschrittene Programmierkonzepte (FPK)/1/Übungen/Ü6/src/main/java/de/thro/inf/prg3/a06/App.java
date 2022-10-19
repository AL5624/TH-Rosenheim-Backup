package de.thro.inf.prg3.a06;

import com.google.gson.Gson;
import de.thro.inf.prg3.a06.model.JokeResponse;
import de.thro.inf.prg3.a06.model.JokesResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) throws IOException
	{
		// TODO fetch a random joke and print it to STDOUT
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://api.icndb.com")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		ICNDBApi Interface = retrofit.create(ICNDBApi.class);
		Call<JokeResponse> call = Interface.getRandomJoke();
		JokeResponse JokeResponse = call.execute().body();

		System.out.println(JokeResponse.getValue().getContent());


		String[] categoriesToInclude = {"nerdy"};
		Call<JokeResponse> call2 = Interface.getRandomJoke(categoriesToInclude);
		JokeResponse JokeResponse2 = call2.execute().body();

		System.out.println(JokeResponse2.getValue().getContent());
		System.out.println(JokeResponse2.getValue().getRubrics());

		JokeResponse JokeResponse3;

		Call<JokeResponse> call3 = Interface.getJokeById(453);
		JokeResponse3 = call3.execute().body();

		System.out.println(JokeResponse3.getValue().getNumber());
		System.out.println(JokeResponse3.getValue().getContent());

		Call<JokesResponse> call4 = Interface.getRandeomJokes(3);
		JokesResponse JokesResponse = call4.execute().body();

		System.out.println(JokesResponse.getValue()[0].getContent());
		System.out.println(JokesResponse.getValue()[1].getContent());
		System.out.println(JokesResponse.getValue()[2].getContent());






	}
}
