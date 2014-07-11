package de.pro_crafting.generator.criteria;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.Point;

public class CuboidCriteria implements Criteria{
	
	private Criteria wraped;
	
	public boolean matches(BlockData block, Point point) {
		return wraped != null ? wraped.matches(block, point) : true;
	}

	public void wrap(Criteria criteria) {
		if (wraped == null)
		{
			wraped = criteria;
		}
		else
		{
			wraped.wrap(criteria);
		}
	}
}
