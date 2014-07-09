package de.pro_crafting.generator.job;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.JobState;
import de.pro_crafting.generator.Point;
import de.pro_crafting.generator.criteria.Criteria;

public interface Job 
{
	public Point nextMatchingPosition();
	public JobState getState();
	public void setState(JobState state);
	public Criteria getCriteria();
	public BlockData getBlockData();
}
