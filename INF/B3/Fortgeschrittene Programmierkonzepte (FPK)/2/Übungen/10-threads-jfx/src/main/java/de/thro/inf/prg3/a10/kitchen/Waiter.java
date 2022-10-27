package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.model.Dish;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Waiter implements Runnable {
	private String name;
	private I_KitchenHatch kitchenHatch;
	private ProgressReporter progressReporter;

	@Override
	public void run() {
		Dish dish = this.kitchenHatch.dequeueDish();

		while (dish != null) {
			try {
				this.serveDish(dish);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			this.progressReporter.updateProgress();

			dish = this.kitchenHatch.dequeueDish();
		}

		this.progressReporter.notifyWaiterLeaving();
	}

	private void serveDish(Dish dish) throws InterruptedException {
		Thread.sleep((int)(Math.random() * 1000d));
	}
}
