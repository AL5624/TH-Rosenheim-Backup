package de.thro.inf.prg3.a11;

import de.thro.inf.prg3.a11.openmensa.IOpenMensaAPI;
import de.thro.inf.prg3.a11.openmensa.OpenMensaAPIService;
import de.thro.inf.prg3.a11.openmensa.model.Canteen;
import de.thro.inf.prg3.a11.openmensa.model.Meal;
import de.thro.inf.prg3.a11.openmensa.model.PageInfo;
import de.thro.inf.prg3.a11.openmensa.model.State;
import retrofit2.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;


public enum MenuStrategy {

	SHOW_CANTEENS {
		@Override
		void execute() {
			System.out.println("Fetching canteens [");
			IOpenMensaAPI mensaAPI = OpenMensaAPIService.getInstance().getOpenMensaAPI();

			Consumer<Integer> printCanteens = totalPages -> {
				List<CompletableFuture<Void>> cfl = new ArrayList<>();

				for (int i = 1; i <= totalPages; i++) {
					cfl.add(mensaAPI.getCanteens(i).thenAccept((list) -> {
						synchronized (System.out) {
							list.forEach(canteen -> {
								System.out.println("  {");
								System.out.println("    name: " + canteen.getName() + ";");
								System.out.println("    id: " + canteen.getId() + ";");
								System.out.println("    address: " + canteen.getAddress() + ";");
								System.out.println("  },");
								System.out.notifyAll();
							});
						}
					}));
				}

				cfl.forEach(CompletableFuture::join);
			};

			CompletableFuture<Void> c = mensaAPI.getCanteens()
				.thenApplyAsync(PageInfo::extractFromResponse)
				.thenApplyAsync(PageInfo::getTotalCountOfPages)
				.thenAccept(printCanteens)
				.exceptionally(e -> {
					System.out.println(e.getMessage());
					return null;
				});

			try {
				c.get();
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e.getMessage());
				return;
			}

			/*
			Response<List<Canteen>> canteensResponse;
			try {
				canteensResponse = mensaAPI.getCanteens().get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}

			int totalPages = PageInfo.extractFromResponse(canteensResponse).getTotalCountOfPages();

			List<CompletableFuture<List<Canteen>>> promises = new ArrayList<>();
			for(int i = 1; i <= totalPages; i++) {
				promises.add(mensaAPI.getCanteens(i));
			}

			List<List<Canteen>> canteenBook = new ArrayList<>();
			promises.forEach(promise -> {
				try {
					canteenBook.add(promise.get());
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException(e);
				}
			});

			canteenBook.forEach(canteenPage -> canteenPage.forEach(canteenEntry -> {
				System.out.println("  {");
				System.out.println("    name: " + canteenEntry.getName() + ";");
				System.out.println("    id: " + canteenEntry.getId() + ";");
				System.out.println("    address: "  + canteenEntry.getAddress() + ";");
				System.out.println("  },");
			}));
			*/

			System.out.println("]");
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
			IOpenMensaAPI mensaAPI = OpenMensaAPIService.getInstance().getOpenMensaAPI();

			if (currentCanteenId <= 0) {
				System.out.println("Please set a valid canteen id!");
				return;
			}

			State canteenState;
			try {
				canteenState = mensaAPI.getCanteenState(currentCanteenId, dateFormat.format(currentDate.getTime())).get();
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e.getMessage());
				System.out.println("Invalid date or canteend id!");
				return;
			}

			if (canteenState.isClosed()) {
				System.out.println("Canteen closed. Select another date.");
				return;
			}

			List<Meal> meals;

			try {
				meals = mensaAPI.getMeals(currentCanteenId, dateFormat.format(currentDate.getTime())).get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}

			System.out.println("Meals: [");

			AtomicInteger i = new AtomicInteger();
			meals.forEach(meal -> {
				System.out.println("  " + i.getAndIncrement() + ": {");
				System.out.println("    name: " + meal.getName() + ";");
				System.out.println("    id: " + meal.getId() + ";");
				System.out.println("    category: " + meal.getCategory() + ";");
				System.out.println("  },");
			});

			System.out.println("]");

			/* TODO fetch all meals for the currently selected canteen
			 * to avoid errors retrieve at first the state of the canteen and check if the canteen is opened at the selected day
			 * don't forget to check if a canteen was selected previously! */
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
	private static final IOpenMensaAPI openMensaAPI = OpenMensaAPIService.getInstance().getOpenMensaAPI();
	private static final Calendar currentDate = Calendar.getInstance();
	private static int currentCanteenId = -1;

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
