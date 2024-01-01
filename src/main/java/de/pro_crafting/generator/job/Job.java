package de.pro_crafting.generator.job;

import org.bukkit.World;

import com.pro_crafting.mc.common.Point;
import com.pro_crafting.mc.common.Size;
import de.pro_crafting.generator.JobState;

public interface Job 
{
	boolean next();
	JobState getState();
	void setState(JobState state);
	World getWorld();
	int getAffectedBlocks();
	Point getOrigin();
	Size getSize();
}
