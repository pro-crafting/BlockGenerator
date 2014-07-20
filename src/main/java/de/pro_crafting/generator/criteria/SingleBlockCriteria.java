package de.pro_crafting.generator.criteria;

import org.bukkit.Material;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.Point;

public class SingleBlockCriteria implements Criteria {
	
	Criteria wraped;
	Material type;
	
	public SingleBlockCriteria(Material type) {
		this.type = type;
	}
	
	public boolean matches(BlockData block, Point point) {
		boolean shouldSet = block.getType() == this.type;
		if (wraped != null && shouldSet) {
			return wraped.matches(block, point);
		}
		return shouldSet;
	}

	public void wrap(Criteria criteria) {
		if (wraped == null) {
			wraped = criteria;
		} else {
			wraped.wrap(criteria);
		}
	}

}
