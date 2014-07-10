package de.pro_crafting.generator.provider;

import org.bukkit.Material;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.Point;

public class SingleBlockProvider implements Provider{
	private BlockData blockData;

	public SingleBlockProvider(Material type, byte dataByte)
	{
		this.blockData = new BlockData(type, dataByte);
	}
	
	public BlockData getBlockData(Point point) {
		return this.blockData;
	}
}
