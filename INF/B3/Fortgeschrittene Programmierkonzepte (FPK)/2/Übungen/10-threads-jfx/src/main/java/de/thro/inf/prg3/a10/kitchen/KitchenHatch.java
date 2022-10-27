package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;

import java.util.ArrayDeque;
import java.util.Deque;

public class KitchenHatch implements I_KitchenHatch {
	private final int maxDishes;
	private final Deque<Order> orders;
	private final Deque<Dish> dishes = new ArrayDeque<>();


	public KitchenHatch(int maxDishes, Deque<Order> orders) {
		this.maxDishes = maxDishes;
		this.orders = orders;
	}

	@Override
	public int getMaxDishes() {
		return this.maxDishes;
	}

	@Override
	public Order dequeueOrder(long timeout) {
		Order order = null;

		synchronized (this.orders) {
			order = this.orders.pollFirst();
		}
		return order;
	}

	@Override
	public int getOrderCount() {
		int size = 0;

		synchronized (orders) {
			size = orders.size();
		}

		return size;
	}

	@Override
	public Dish dequeueDish(long timeout) {
		Dish dish = null;

		synchronized (this.dishes) {
			while (this.dishes.size() == 0) {
				try {
					System.out.println("waiter waiting");
					dishes.wait(timeout);
					if (this.getOrderCount() == 0) {
						dishes.wait(timeout);
						if (this.dishes.size() == 0) {
							break;
						}
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			dish = this.dishes.pollFirst();

			this.dishes.notifyAll();
		}

		return dish;
	}

	@Override
	public void enqueueDish(Dish dish) {
		synchronized (this.dishes) {
			while (this.dishes.size() >= this.getMaxDishes()) {
				try {
					System.out.println("cook waiting");
					this.dishes.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}

			this.dishes.push(dish);

			this.dishes.notifyAll();
		}

		System.out.println(this.getOrderCount() + " orders to go");
	}

	@Override
	public int getDishesCount() {
		int size = 0;

		synchronized (this.dishes) {
			size = this.dishes.size();
		}

		return size;
	}
}
