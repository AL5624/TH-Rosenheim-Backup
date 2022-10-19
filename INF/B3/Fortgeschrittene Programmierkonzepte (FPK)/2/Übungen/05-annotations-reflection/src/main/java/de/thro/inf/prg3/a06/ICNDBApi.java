package de.thro.inf.prg3.a06;

import de.thro.inf.prg3.a06.model.JokeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public interface ICNDBApi {

	@GET("/jokes/random")
	Call<JokeResponse> getRandomJoke();

}
