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
	
	public SingleBlockProvider(Criteria criteria, Point min, Point max) {
		this.criteria = criteria;
		this.min = min;
		this.max = max;
	}
	
	public SingleBlockProvider(Material type, byte dataByte) {
		this.blockData = new BlockData(type, dataByte);
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
