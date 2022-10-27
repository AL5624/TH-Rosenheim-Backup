package de.thro.inf.prg3.a10.controller;

import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.kitchen.Cook;
import de.thro.inf.prg3.a10.kitchen.I_KitchenHatch;
import de.thro.inf.prg3.a10.kitchen.KitchenHatch;
import de.thro.inf.prg3.a10.kitchen.Waiter;
import de.thro.inf.prg3.a10.model.Order;
import de.thro.inf.prg3.a10.util.NameGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ResourceBundle;

import static de.thro.inf.prg3.a10.KitchenHatchConstants.*;

public class MainController implements Initializable {
	private final ProgressReporter progressReporter;

	private final NameGenerator nameGenerator;

	private final I_KitchenHatch kitchenHatch;

	@FXML
	private ProgressIndicator waitersBusyIndicator;

	@FXML
	private ProgressIndicator cooksBusyIndicator;

	@FXML
	private ProgressBar kitchenHatchProgress;

	@FXML
	private ProgressBar orderQueueProgress;

	public MainController() {
		this.nameGenerator = new NameGenerator();

		Deque<Order> orders = new ArrayDeque<>();

		for (int i = 0; i < ORDER_COUNT; i++) {
			orders.push(new Order(nameGenerator.getRandomDish()));
		}

		this.kitchenHatch = new KitchenHatch(KITCHEN_HATCH_SIZE, orders);

		this.progressReporter = new ProgressReporter(kitchenHatch, COOKS_COUNT, WAITERS_COUNT, ORDER_COUNT, KITCHEN_HATCH_SIZE);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderQueueProgress.progressProperty().bindBidirectional(this.progressReporter.orderQueueProgressProperty());
		kitchenHatchProgress.progressProperty().bindBidirectional(this.progressReporter.kitchenHatchProgressProperty());
		waitersBusyIndicator.progressProperty().bindBidirectional(this.progressReporter.waitersBusyProperty());
		cooksBusyIndicator.progressProperty().bind(this.progressReporter.cooksBusyProperty());

		/* TODO create the cooks and waiters, pass the kitchen hatch and the reporter instance and start them */

		this.progressReporter.updateProgress();

		for (int i = 0; i < COOKS_COUNT; i++) {
			new Thread(new Cook(nameGenerator.generateName(), this.kitchenHatch, this.progressReporter)).start();
		}

		for (int i = 0; i < WAITERS_COUNT; i++) {
			new Thread(new Waiter(nameGenerator.generateName(), this.kitchenHatch, this.progressReporter)).start();
		}
	}
}
