package de.pro_crafting.generator.criteria;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;

public class CuboidCriteria implements Criteria{
	
	private Criteria wraped;
	
	public boolean matches(BlockData block, Point point) {
		if (wraped != null) {
			return wraped.matches(block, point);
		}
		return true;
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
