package de.pro_crafting.generator.provider;

import com.pro_crafting.mc.common.Point;
import de.pro_crafting.generator.criteria.Criteria;
import org.bukkit.block.data.BlockData;

public interface Provider {
	BlockData getBlockData(Point point, BlockData block);
	Criteria getCriteria();
}
