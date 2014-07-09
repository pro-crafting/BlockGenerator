package de.pro_crafting.generator;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

public class BlockData {
	private Point point;
	private UUID world;
	private Material type;
	private byte dataByte;
	
	public BlockData(Point point, UUID world, Material type, byte dataByte)
	{
		this.point = point;
		this.world = world;
		this.type = type;
		this.dataByte = dataByte;
	}
	
	public Point getPoint()
	{
		return point;
	}
	
	public World getWorld()
	{
		return Bukkit.getWorld(world);
	}
	
	public Material getType()
	{
		return type;
	}
	
	public byte getDataByte()
	{
		return dataByte;
	}
}
