package de.pro_crafting.generator.criteria;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;

public interface Criteria {
	boolean matches(BlockData block, Point point);
	void wrap(Criteria criteria);
}
