package de.pro_crafting.generator.provider;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.Point;

public class SingleBlockProvider implements Provider{
	private BlockData blockData;

	public SingleBlockProvider(BlockData blockData)
	{
		this.blockData = blockData;
	}
	
	public BlockData getBlockData(Point point) {
		return this.blockData;
	}
	
}
