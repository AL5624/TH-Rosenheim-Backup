package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

public abstract class FilterBase implements MealsFilter
{
	protected boolean include(Meal m)
	{
		return false;
	};
}
