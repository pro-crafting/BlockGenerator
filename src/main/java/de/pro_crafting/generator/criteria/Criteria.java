package de.pro_crafting.generator.criteria;

import de.pro_crafting.generator.BlockData;

public interface Criteria {
	boolean matches(BlockData block);
	void wrap(Criteria criteria);
}
