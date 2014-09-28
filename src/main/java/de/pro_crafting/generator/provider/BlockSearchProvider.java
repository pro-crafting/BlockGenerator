package de.pro_crafting.generator.provider;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.criteria.Criteria;

public class BlockSearchProvider implements Provider {
	private Criteria criteria;
	private Point min;
	private Point max;
	
	public BlockSearchProvider(Criteria criteria, Point min, Point max) {
		this.criteria = criteria;
		this.min = min;
		this.max = max;
	}
	
	public BlockData getBlockData(Point point, BlockData block) {
		criteria.matches(point, block);
		return block;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public Point getMin() {
		return this.min;
	}

	public Point getMax() {
		return this.max;
	}

}
