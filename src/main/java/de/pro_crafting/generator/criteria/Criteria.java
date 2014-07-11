package de.pro_crafting.generator.criteria;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.Point;

public interface Criteria {
	boolean matches(BlockData block, Point point);
	void wrap(Criteria criteria);
}
