package com.pro_crafting.mc.blockgenerator.criteria;

import java.util.ArrayList;
import java.util.List;

import com.pro_crafting.mc.common.Point;
import org.bukkit.block.data.BlockData;

public class SingleBlockFindCriteria {
	private Criteria wraped;
	private BlockData blockData;
	private List<Point> matches;
	
	public SingleBlockFindCriteria(BlockData blockData) {
		this.blockData = blockData;
		this.matches = new ArrayList<>();
	}
	
	public boolean matches(Point point, BlockData block) {
		boolean shouldSet = block.matches(block);
		if (wraped != null && shouldSet) {
			shouldSet = wraped.matches(point, block);
		}
		if (shouldSet) {
			this.matches.add(point);
		}
		return true;
	}

	public void wrap(Criteria criteria) {
		if (wraped == null) {
			wraped = criteria;
		} else {
			wraped.wrap(criteria);
		}
	}
	
	public List<Point> getMatches() {
		return this.matches;
	}
}
