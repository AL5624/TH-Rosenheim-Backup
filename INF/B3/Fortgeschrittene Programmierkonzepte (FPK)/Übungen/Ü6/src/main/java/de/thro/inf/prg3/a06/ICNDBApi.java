package de.thro.inf.prg3.a06;

import de.thro.inf.prg3.a06.model.JokeResponse;
import de.thro.inf.prg3.a06.model.JokesResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public interface ICNDBApi
{
	@GET("/jokes/random")
	Call<JokeResponse> getRandomJoke();

	@GET("/jokes/random")
	Call<JokeResponse> getRandomJoke(@Query("limitTo") String [] categoriesToInclude);

	@GET("/jokes/random/{count}")
	Call<JokesResponse> getRandeomJokes(@Path("count")int count);

	@GET("/jokes/{id}")
	Call<JokeResponse> getJokeById(@Path("id")int id);

}
