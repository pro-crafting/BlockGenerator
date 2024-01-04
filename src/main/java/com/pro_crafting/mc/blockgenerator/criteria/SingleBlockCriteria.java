package com.pro_crafting.mc.blockgenerator.criteria;

import com.pro_crafting.mc.common.Point;
import org.bukkit.block.data.BlockData;

public class SingleBlockCriteria implements Criteria {
	
	private Criteria wraped;
	private BlockData blockData;
	
	public SingleBlockCriteria(BlockData blockData) {
		this.blockData = blockData;
	}
	
	public boolean matches(Point point, BlockData block) {
		boolean shouldSet = block.matches(blockData);
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
