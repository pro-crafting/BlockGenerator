package de.pro_crafting.generator.criteria;

import com.pro_crafting.mc.common.Point;
import org.bukkit.block.data.BlockData;

public interface Criteria {
	boolean matches(Point point, BlockData block);
	void wrap(Criteria criteria);
}
