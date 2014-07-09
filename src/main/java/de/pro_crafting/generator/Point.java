package de.pro_crafting.generator;

import org.bukkit.Location;
import org.bukkit.World;

public class Point {
	private int x;
	private int y;
	private int z;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point(Location from) {
		this.x = from.getBlockX();
		this.y = from.getBlockY();
		this.z = from.getBlockZ();
	}

	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public Location toLocation(World world) {
		return new Location(world, x, y, z);
	}
}
