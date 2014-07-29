package de.pro_crafting.generator.provider;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;

public interface Provider {
	BlockData getBlockData(Point point);
}
