package de.thro.inf.prg3.a06;

import de.thro.inf.prg3.a06.model.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.*;
/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		// TODO fetch a random joke and print it to STDOUT
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://api.icndb.com")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		ICNDBApi service = retrofit.create(ICNDBApi.class);

		JokeResponse r = service.getRandomJoke().execute().body();
	}

}
