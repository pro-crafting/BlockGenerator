package de.pro_crafting.generator.provider;

import org.bukkit.Material;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.criteria.Criteria;

public class SingleBlockProvider implements Provider{
	private BlockData blockData;
	private Criteria criteria;
	private Point min;
	private Point max;
	
	public SingleBlockProvider(Criteria criteria, Material type, byte dataByte, Point min, Point max) {
		this.criteria = criteria;
		this.blockData = new BlockData(type, dataByte);
		this.min = min;
		this.max = max;
	}
	
	public BlockData getBlockData(Point point, BlockData block) {
		if (!criteria.matches(point, block)) {
			return block;
		}
		return this.blockData;
	}

	public Criteria getCriteria() {
		return this.criteria;
	}

	public Point getMin() {
		return this.min;
	}

	public Point getMax() {
		return this.max;
	}
}
