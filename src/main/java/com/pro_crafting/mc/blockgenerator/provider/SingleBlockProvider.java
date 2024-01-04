package com.pro_crafting.mc.blockgenerator.provider;

import com.pro_crafting.mc.common.Point;
import com.pro_crafting.mc.blockgenerator.criteria.Criteria;
import org.bukkit.block.data.BlockData;

public class SingleBlockProvider implements Provider{
	private BlockData blockData;
	private Criteria criteria;
	
	public SingleBlockProvider(Criteria criteria, BlockData blockData) {
		this.criteria = criteria;
		this.blockData = blockData;
	}
	
	public BlockData getBlockData(Point point, BlockData block) {
		if (!criteria.matches(point, block)) {
			return block;
		}
		return this.blockData;
	}

	public Criteria getCriteria() {
		return this.criteria;
	}
}
