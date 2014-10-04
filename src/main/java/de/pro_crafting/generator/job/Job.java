package de.pro_crafting.generator.job;

import java.util.Map.Entry;

import org.bukkit.World;

import de.pro_crafting.common.Point;
import de.pro_crafting.common.Size;
import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.JobState;

public interface Job 
{
	public Entry<Point, BlockData> next();
	public JobState getState();
	public void setState(JobState state);
	public World getWorld();
	public int getAffectedBlocks();
	public Point getOrigin();
	public Size getSize();
}
