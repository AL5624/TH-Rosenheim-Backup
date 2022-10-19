package de.thro.inf.prg3.a10.kitchen.workers;

import de.thro.inf.prg3.a10.internals.displaying.ProgressReporter;
import de.thro.inf.prg3.a10.kitchen.KitchenHatch;
import de.thro.inf.prg3.a10.model.Dish;

public class Waiter implements Runnable
{
	String name;
	ProgressReporter progressReporter;
	KitchenHatch kitchenHatch;

	public Waiter(String name, KitchenHatch kitchenHatch, ProgressReporter progressReporter)
	{
		this.name = name;
		this.kitchenHatch = kitchenHatch;
		this.progressReporter = progressReporter;
	}
	@Override
	public void run()
	{
		Dish dish;
		do
		{
			if(kitchenHatch.getOrderCount() == 0 && kitchenHatch.getDishesCount() == 0)
			{
				try
				{
					Thread.sleep(5000);
					if(kitchenHatch.getOrderCount() == 0 && kitchenHatch.getDishesCount() == 0)break;
				} catch (InterruptedException e)
				{
				}
			}
			dish = kitchenHatch.dequeueDish();

			try
			{
				Thread.sleep((long) Math.random() * 1000);

			} catch (InterruptedException e) {}
			if(kitchenHatch.getOrderCount() == 0)System.out.println(dish);
			System.out.println("whileschleife2");
			progressReporter.updateProgress();
		}while(dish != null);
		progressReporter.updateProgress();
		progressReporter.notifyWaiterLeaving();
	}
}
