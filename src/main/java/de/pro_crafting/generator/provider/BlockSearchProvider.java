package de.pro_crafting.generator.provider;

import com.pro_crafting.mc.common.Point;
import de.pro_crafting.generator.criteria.Criteria;
import org.bukkit.block.data.BlockData;

public class BlockSearchProvider implements Provider {
	private Criteria criteria;

	public BlockSearchProvider(Criteria criteria) {
		this.criteria = criteria;
	}
	
	public BlockData getBlockData(Point point, BlockData block) {
		criteria.matches(point, block);
		return block;
	}

	public Criteria getCriteria() {
		return criteria;
	}
}
