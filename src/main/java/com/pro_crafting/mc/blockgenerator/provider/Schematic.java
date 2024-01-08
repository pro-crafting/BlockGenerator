package com.pro_crafting.mc.blockgenerator.provider;

import java.util.HashMap;
import java.util.Map;

import com.pro_crafting.mc.common.Point;
import com.pro_crafting.mc.common.Size;
import org.bukkit.block.data.BlockData;

public class Schematic {
	private Point origin;
	private Size size;
	private Map<Point, BlockData> blocks;
	
	public Schematic(Point origin, Size size) {
		this.blocks = new HashMap<>();
		this.origin = origin;
		this.size = size;
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public BlockData get(Point point) {
		return this.blocks.get(point);
	}
	
	public void set(Point point, BlockData block) {
		this.blocks.put(point, block);
	}
}
