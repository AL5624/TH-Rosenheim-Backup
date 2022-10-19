package de.thro.inf.prg3.a11;

import de.thro.inf.prg3.a11.openmensa.OpenMensaAPI;
import de.thro.inf.prg3.a11.openmensa.OpenMensaAPIService;
import de.thro.inf.prg3.a11.openmensa.model.Canteen;
import de.thro.inf.prg3.a11.openmensa.model.Meal;
import de.thro.inf.prg3.a11.openmensa.model.PageInfo;
import de.thro.inf.prg3.a11.openmensa.model.State;
import retrofit2.Response;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public enum MenuStrategy {



	SHOW_CANTEENS {
		@Override
		void execute() {
			System.out.print("Fetching canteens :");
			/* TODO fetch all canteens and print them to STDOUT
			 * at first get a page without an index to be able to extract the required pagination information
			 * afterwards you can iterate the remaining pages
			 * keep in mind that you should await the process as the user has to select canteen with a specific id */

			System.out.println("\n");
			for (Canteen c : listCanteen)
			{
				System.out.println(c.toString());
			}
			System.out.println("\n");


		}
	},
	SET_CANTEEN {
		@Override
		void execute() {
			readCanteen();
		}
	},
	SHOW_MEALS {
		@Override
		void execute() {
			/* TODO fetch all meals for the currently selected canteen
			 * to avoid errors retrieve at first the state of the canteen and check if the canteen is opened at the selected day
			 * don't forget to check if a canteen was selected previously! */

			if(currentCanteenId < 1 || currentCanteenId > listCanteen.size())
			{
				System.out.println("Please select a canteen first.");
				return;
			}

			try
			{
				CompletableFuture<State> state = openMensaAPI.getCanteenState(currentCanteenId, dateFormat.format(currentDate.getTime()));
				state.join();
				State s = state.get();
				if(s.isClosed() == true)
				{
					System.out.println("Canteen is closed on that day.");
					return;
				}
				CompletableFuture<List<Meal>> meals = openMensaAPI.getMeals(currentCanteenId, dateFormat.format(currentDate.getTime()));
				meals.join();
				List<Meal> mealList = meals.get();

				for(Meal m: mealList)
				{
					System.out.println(m.toString());
				}
			}
			catch (Exception e)
			{
				System.out.println("openMensaAPI.getMeals() failed.\n pleas try again...");
			}
		}
	},
	SET_DATE {
		@Override
		void execute() {
			readDate();
		}
	},
	QUIT {
		@Override
		void execute() {
			System.exit(0);
		}
	};

	abstract void execute();

	private static final String OPEN_MENSA_DATE_FORMAT = "yyyy-MM-dd";

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(OPEN_MENSA_DATE_FORMAT, Locale.getDefault());
	private static final Scanner inputScanner = new Scanner(System.in);
	private static final OpenMensaAPI openMensaAPI = OpenMensaAPIService.getInstance().getOpenMensaAPI();
	private static final Calendar currentDate = Calendar.getInstance();
	private static int currentCanteenId = 1;
	private static List<Canteen> listCanteen = new ArrayList<>();

	/**
	 * Utility method to select a canteen
	 */
	protected static void readCanteen() {
		/* typical input reading pattern */
		boolean readCanteenId = false;
		do {
			try {
				System.out.println("Enter canteen id:");
				currentCanteenId = inputScanner.nextInt();
				readCanteenId = true;
			} catch (Exception e) {
				System.out.println("Sorry could not read the canteen id");
			}
		} while (!readCanteenId);
	}

	public static void initialisiere(int status)
	{
		PageInfo pageInfo;
		try
		{
			CompletableFuture<Response<List<Canteen>>> futureCanteenList = openMensaAPI.getCanteens();
			futureCanteenList.join();
			pageInfo = PageInfo.extractFromResponse(futureCanteenList.get());

			for(int i = pageInfo.getCurrentPageIndex(); i <= pageInfo.getTotalCountOfPages(); i++)
			{
				CompletableFuture<List<Canteen>> canteenPage = openMensaAPI.getCanteens(i);
				canteenPage.join();
				listCanteen.addAll(canteenPage.get());
			}
		}
		catch (Exception e)
		{
			if(status == 0)
			{
				System.out.println("PageInfo.extractFromResponse failed again.\n exit...");
				System.exit(0);
			}

			System.out.println("PageInfo.extractFromResponse failed.\n trying again...");
			initialisiere(0);
		}

	}

	/**
	 * Utility method to read a date and update the calendar
	 */
	protected static void readDate() {
		/* typical input reading pattern */
		boolean readDate = false;
		do {
			try {
				System.out.println("Please enter date in the format yyyy-mm-dd:");
				Date d = dateFormat.parse(inputScanner.next());
				currentDate.setTime(d);
				readDate = true;
			} catch (ParseException p) {
				System.out.println("Sorry, the entered date could not be parsed.");
			}
		} while (!readDate);

	}
}
