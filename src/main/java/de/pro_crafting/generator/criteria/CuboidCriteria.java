package de.pro_crafting.generator.criteria;

import de.pro_crafting.generator.BlockData;

public class CuboidCriteria implements Criteria{
	
	private Criteria wraped;
	
	public boolean matches(BlockData block) {
		return wraped != null ? wraped.matches(block) : true;
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
