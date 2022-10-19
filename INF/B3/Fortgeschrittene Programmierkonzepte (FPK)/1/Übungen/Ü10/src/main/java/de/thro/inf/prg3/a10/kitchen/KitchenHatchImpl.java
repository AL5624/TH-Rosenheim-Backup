package de.thro.inf.prg3.a10.kitchen;

import de.thro.inf.prg3.a10.model.Dish;
import de.thro.inf.prg3.a10.model.Order;
import de.thro.inf.prg3.a10.util.NameGenerator;

import java.util.Deque;
import java.util.LinkedList;

import static de.thro.inf.prg3.a10.KitchenHatchConstants.KITCHEN_HATCH_SIZE;
import static de.thro.inf.prg3.a10.KitchenHatchConstants.ORDER_COUNT;

public class KitchenHatchImpl implements KitchenHatch
{
	private final int maxMeals;
	private Deque<Order> orders;
	private Deque<Dish> dishes = new LinkedList<>();
	private int orderCount;
	private int dishesCount = 0;
	public KitchenHatchImpl(int maxMeals, Deque<Order> orders)
	{
		this.maxMeals = maxMeals;
		this.orders = orders;
		orderCount = orders.size();
	}

	@Override
	public int getMaxDishes()
	{
		return maxMeals;
	}

	@Override
	public Order dequeueOrder(long timeout)
	{
		Order tmp = null;
		synchronized (orders)
		{
			if(getOrderCount() != 0)
			{
				tmp = orders.getFirst();
				orders.remove(orders.getFirst());
				--orderCount;
				System.out.println("orderCount: " + orderCount);
			}
		}
		return tmp;
	}

	@Override
	public Dish dequeueDish(long timeout)
	{
		Dish tmp = null;
		if(dishes != null)
		{
			synchronized (dishes)
			{
				while (getDishesCount() == 0)
				{
					try
					{
						dishes.wait((long) Math.random() * 1000);
						System.out.println("whileschleif1");
					} catch (InterruptedException e)
					{
					}
					if (getOrderCount() == 0)
					{
						try
						{
							dishes.wait(3000);
						} catch (InterruptedException e)
						{
						}
						break;
					}
				}
				if (getDishesCount() > 0)
				{
					tmp = dishes.getFirst();

					dishes.remove(dishes.getFirst());
					--dishesCount;
					System.out.println("dequeue Dish: " + dishesCount);

					return tmp;
				}
				System.out.println("last");
				dishes.notifyAll();
				dishes = null;
				return tmp;
			}
		}
		return null;
	}

	@Override
	public void enqueueDish(Dish m)
	{
		if(dishes != null)
		{
			synchronized (dishes)
			{
				while (getDishesCount() >= getMaxDishes())
				{
					try
					{
						dishes.wait((long) Math.random() * 3000);
					} catch (InterruptedException e)
					{
					}
				}

				dishes.add(m);
				++dishesCount;
				System.out.println("enqueue Dish:" + dishesCount);
				dishes.notifyAll();
			}
		}
	}

	@Override
	public int getDishesCount()
	{
		return dishesCount;
	}

	@Override
	public int getOrderCount()
	{
		return orderCount;
	}
}
