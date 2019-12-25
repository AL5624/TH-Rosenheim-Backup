package de.thro.inf.prg3.a08.services;

import com.google.gson.Gson;
import de.thro.inf.prg3.a08.api.OpenMensaAPI;
import javafx.collections.FXCollections;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class OpenMensaAPIService
{
	private OpenMensaAPI mensaAPIinstance = null;
	private static OpenMensaAPIService instance = null;
	private final Gson gson = new Gson();


	private OpenMensaAPIService()
	{
		Retrofit retrofit = new Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl("http://openmensa.org/api/v2/")
			.build();

		mensaAPIinstance =  retrofit.create(OpenMensaAPI.class);
	}

	public static OpenMensaAPIService getInstance()
	{
		if(instance == null)
		{
			instance = new OpenMensaAPIService();
		}

		return instance;
	}

	public OpenMensaAPI getAPI()
	{
		return mensaAPIinstance;
	}


}
