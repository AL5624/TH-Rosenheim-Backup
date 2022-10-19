package de.thro.inf.prg3.a08.filtering;

import de.thro.inf.prg3.a08.model.Meal;

import java.util.List;

public class VegetarianStrategy extends FilterBase
{
	@Override
	protected boolean include(Meal m)
	{
		for(String s: m.getNotes())
		{
			if(s.contains("fleischlos") || s.contains("vegan") || s.contains("vegetarisch")) return true;
		}
		return false;
	}

	@Override
	public List<Meal> filter(List<Meal> meals)
	{
		List<Meal> tmp = null;
		for(Meal m: meals)
		{
			if(include(m) == true) tmp.add(m);
		}
		return tmp;
	}
}
