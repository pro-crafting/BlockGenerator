package com.pro_crafting.mc.blockgenerator.criteria;

import org.bukkit.Material;

import com.pro_crafting.mc.common.Point;
import org.bukkit.block.data.BlockData;

public class SingleBlockCriteria implements Criteria {
	
	Criteria wraped;
	Material type;
	
	public SingleBlockCriteria(Material type) {
		this.type = type;
	}
	
	public boolean matches(Point point, BlockData block) {
		boolean shouldSet = block.getMaterial() == this.type;
		if (wraped != null && shouldSet) {
			return wraped.matches(point, block);
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
