package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Cook implements Runnable {
	private String name;
	private I_KitchenHatch kitchenHatch;
	private ProgressReporter progressReporter;

	@Override
	public void run() {
		Order order = this.kitchenHatch.dequeueOrder();

		while (order != null) {
			Dish dish = new Dish(order.getMealName());

			try {
				Thread.sleep(dish.getCookingTime());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			this.kitchenHatch.enqueueDish(dish);

			this.progressReporter.updateProgress();

			order = this.kitchenHatch.dequeueOrder();
		}

		this.progressReporter.notifyCookLeaving();
	}
}
