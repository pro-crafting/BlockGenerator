package de.pro_crafting.generator.provider;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.Point;

public interface Provider {
	BlockData getBlockData(Point point);
}
