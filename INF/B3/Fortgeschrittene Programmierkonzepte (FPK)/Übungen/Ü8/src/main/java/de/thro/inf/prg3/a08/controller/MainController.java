package de.thro.inf.prg3.a08.controller;

import com.google.gson.Gson;
import de.thro.inf.prg3.a08.api.OpenMensaAPI;
import de.thro.inf.prg3.a08.model.Meal;
import de.thro.inf.prg3.a08.services.OpenMensaAPIService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javafx.event.EventHandler;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controller for main.fxml
 *
 * @author Peter Kurfer
 */
public class MainController implements Initializable {

	/**
	 * Logger e.g. to debug multi-threading issues
	 */
	private static final Logger logger;
	private final Gson gson = new Gson();
	private final OpenMensaAPI api;


	/**
	 * DateFormat instance to generate required date format string for OpenMensa API
	 */
	private static final DateFormat openMensaDateFormat;


	private final ObservableList<Meal> meals;

	@FXML
	private Button btnClose;

	@FXML
	private Button Refresh;


	/**
	 * Binding of ChoiceBox UI element to filter for certain types of meals
	 */
	@FXML
	private ChoiceBox<String> filterChoiceBox;

	/**
	 * Binding of ListView UI element to display meals
	 */
	@FXML
	private ListView<Meal> mealsListView;

	/*
	  static initializer to initialize fields in class
	 */
	static {
		logger = LogManager.getLogger(MainController.class);
		openMensaDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	}

	/**
	 * Default constructor
	 */
	public MainController() {
		meals = FXCollections.observableArrayList();
		OpenMensaAPIService svc = OpenMensaAPIService.getInstance();
		api = svc.getAPI();
	}

	/**
	 * Initialization method of the UI controller
	 * Called after the FXML fields are assigned
	 *
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mealsListView.setItems(meals);
		filterChoiceBox.setItems(FXCollections.observableList(Arrays.asList(gson.fromJson(new InputStreamReader(getClass().getResourceAsStream("/filters.json")), String[].class))));
		doGetMeals();

		btnClose.setOnAction(event ->
		{
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
			System.exit(0);
		});
	}

	/**
	 * Handles fetching of meals from OpenMensa API
	 */
	private void doGetMeals() {
		api.getMeals(openMensaDateFormat.format(new Date())).enqueue(new Callback<List<Meal>>() {
			@Override
			public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
				logger.debug("Got response");
				if (!response.isSuccessful() || response.body() == null) {
					logger.error(String.format("Got response with not successfull code %d", response.code()));
					Platform.runLater(() -> {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText("Unsuccessful HTTP call");
						alert.setContentText("Failed to get meals from OpenMensaAPI");
						alert.show();
					});
					return;
				}

				meals.clear();
				meals.addAll(response.body());
			}

			@Override
			public void onFailure(Call<List<Meal>> call, Throwable t) {
				logger.error("Failed to fetch meals");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Failed HTTP call");
				alert.setContentText("Failed to submit HTTP call to fetch meals.");
				alert.show();
			}
		});
	}
}
