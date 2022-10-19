package de.thro.inf.prg3.a10.controller;

import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.kitchen.KitchenHatch;
import de.thro.inf.prg3.a10.kitchen.KitchenHatchImpl;
import de.thro.inf.prg3.a10.kitchen.workers.Cook;
import de.thro.inf.prg3.a10.kitchen.workers.Waiter;
import de.thro.inf.prg3.a10.model.Order;
import de.thro.inf.prg3.a10.util.NameGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static de.thro.inf.prg3.a10.KitchenHatchConstants.*;

public class MainController implements Initializable {

	private final ProgressReporter progressReporter;
	private final KitchenHatch kitchenHatch;
	private final NameGenerator nameGenerator;

	@FXML
	private ProgressIndicator waitersBusyIndicator;

	@FXML
	private ProgressIndicator cooksBusyIndicator;

	@FXML
	private ProgressBar kitchenHatchProgress;

	@FXML
	private ProgressBar orderQueueProgress;

	public MainController() {
		nameGenerator = new NameGenerator();

		//TODO assign an instance of your implementation of the KitchenHatch interface
		Deque<Order> orders = new LinkedList<>();
		while (orders.size() != ORDER_COUNT)
		{
			orders.add(new Order(nameGenerator.generateName()));
		}
		this.kitchenHatch = new KitchenHatchImpl(KITCHEN_HATCH_SIZE, orders);
		this.progressReporter = new ProgressReporter(kitchenHatch, COOKS_COUNT, WAITERS_COUNT, ORDER_COUNT, KITCHEN_HATCH_SIZE);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderQueueProgress.progressProperty().bindBidirectional(this.progressReporter.orderQueueProgressProperty());
		kitchenHatchProgress.progressProperty().bindBidirectional(this.progressReporter.kitchenHatchProgressProperty());
		waitersBusyIndicator.progressProperty().bindBidirectional(this.progressReporter.waitersBusyProperty());
		cooksBusyIndicator.progressProperty().bind(this.progressReporter.cooksBusyProperty());

		/* TODO create the cooks and waiters, pass the kitchen hatch and the reporter instance and start them */

		Cook cook_1 = new Cook("cook1", kitchenHatch, progressReporter);
		Cook cook_2 = new Cook("cook2", kitchenHatch, progressReporter);
		Waiter waiter_1 = new Waiter("waiter1", kitchenHatch, progressReporter);
		Waiter waiter_2 = new Waiter("waiter2", kitchenHatch, progressReporter);
		Waiter waiter_3 = new Waiter("waiter3", kitchenHatch, progressReporter);


		Thread t_cook_1 = new Thread(cook_1);
		Thread t_cook_2 = new Thread(cook_2);
		Thread t_waiter_1 = new Thread(waiter_1);
		//Thread t_waiter_2 = new Thread(waiter_2);
		//Thread t_waiter_3 = new Thread(waiter_3);

		t_cook_1.start();
		t_cook_2.start();
		t_waiter_1.start();
		//t_waiter_2.start();
		//t_waiter_3.start();



	}




}
