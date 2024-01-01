package com.pro_crafting.mc.blockgenerator.provider;

import com.pro_crafting.mc.common.Point;
import com.pro_crafting.mc.blockgenerator.criteria.Criteria;
import org.bukkit.block.data.BlockData;

public interface Provider {
	BlockData getBlockData(Point point, BlockData block);
	Criteria getCriteria();
}
