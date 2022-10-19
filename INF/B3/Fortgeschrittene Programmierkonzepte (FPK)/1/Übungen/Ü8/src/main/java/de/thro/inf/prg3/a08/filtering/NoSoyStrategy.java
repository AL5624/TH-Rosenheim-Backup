package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

import java.util.List;

public class NoSoyStrategy extends FilterBase
{
	@Override
	protected boolean include(Meal m)
	{
		return false;
	}

	@Override
	public List<Meal> filter(List<Meal> meals)
	{
		return null;
	}
}
