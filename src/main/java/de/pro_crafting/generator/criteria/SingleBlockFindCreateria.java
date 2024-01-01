package de.pro_crafting.generator.criteria;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import com.pro_crafting.mc.common.Point;
import org.bukkit.block.data.BlockData;

public class SingleBlockFindCreateria {
	private Criteria wraped;
	private Material type;
	private List<Point> matches;
	
	public SingleBlockFindCreateria(Material type) {
		this.type = type;
		this.matches = new ArrayList<>();
	}
	
	public boolean matches(Point point, BlockData block) {
		boolean shouldSet = block.getMaterial() == this.type;
		if (wraped != null && shouldSet) {
			shouldSet = wraped.matches(point, block);
		}
		if (shouldSet) {
			this.matches.add(point);
		}
		return true;
	}

	public void wrap(Criteria criteria) {
		if (wraped == null) {
			wraped = criteria;
		} else {
			wraped.wrap(criteria);
		}
	}
	
	public List<Point> getMatches() {
		return this.matches;
	}
}
