package com.pro_crafting.mc.blockgenerator.job;

import com.pro_crafting.mc.blockgenerator.JobState;
import org.bukkit.World;

import com.pro_crafting.mc.common.Point;
import com.pro_crafting.mc.common.Size;

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
