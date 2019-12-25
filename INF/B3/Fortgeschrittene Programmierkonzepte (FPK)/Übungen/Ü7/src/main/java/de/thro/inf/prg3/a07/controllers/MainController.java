package de.thro.inf.prg3.a07.controllers;

import de.thro.inf.prg3.a07.api.OpenMensaAPI;
import de.thro.inf.prg3.a07.model.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class MainController implements Initializable {

	// use annotation to tie to component in XML
	@FXML
	private Button btnRefresh;

	@FXML
	private ListView<String> mealsList = new ListView<String>();

	@FXML
	private Button btnClose;

	@FXML
	private CheckBox chkVegetarian;

	public ObservableList<Meal> observableMealList = FXCollections.observableArrayList();

	ObservableList<String> list = FXCollections.observableArrayList();

	public MainController()
	{

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://openmensa.org")
			.addConverterFactory(GsonConverterFactory.create())
			.build();

		OpenMensaAPI Interface = retrofit.create(OpenMensaAPI.class);
		Call<List<Meal>> call = Interface.getMeals("2015-12-07");
		call.clone().enqueue(new Callback<List<Meal>>()
		{
			@Override
			public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response)
			{
				observableMealList = (FXCollections.observableList(response.body()));

				for(Meal m: observableMealList)
				{
					//if(chkVegetarian.isSelected() == true && m.isVegetarian() == true)list.add(m.getName());
					//else if (chkVegetarian.isSelected() == false)list.add(m.getName());
					list.add(m.getName());
				}
			}

			@Override
			public void onFailure(Call<List<Meal>> call, Throwable t)
			{

			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		mealsList.setItems(list);

		// set the event handler (callback)
		btnRefresh.setOnAction(event ->
		{
			// create a new (observable) list and tie it to the view
			// ObservableList<String> list = FXCollections.observableArrayList("Hans", "Dampf");
			list.removeAll(list);
			for(Meal m:observableMealList)
			{
				if(chkVegetarian.isSelected() == true && m.isVegetarian() == true)list.add(m.getName());
				else if (chkVegetarian.isSelected() == false)list.add(m.getName());
			}
			mealsList.setItems(null);
			mealsList.setItems (list);


		});

		btnClose.setOnAction(event ->
		{
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
			System.exit(0);
		});

		chkVegetarian.setOnAction(event ->
		{
			mealsList.setItems(null);
		});
	}
}
