package de.pro_crafting.generator.job;

import org.bukkit.World;
import org.bukkit.block.Block;

import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.JobState;
import de.pro_crafting.generator.JobStateChangedCallback;
import de.pro_crafting.generator.Point;
import de.pro_crafting.generator.criteria.Criteria;
import de.pro_crafting.generator.provider.Provider;

public class SimpleJob implements Job
{
	private int currX;
	private int currY;
	private int currZ;
	private Point currLoc;
	private JobState jobState;
	private Point min;
	private Point max;
	private JobStateChangedCallback callback;
	private Criteria criteria;
	private Provider provider;
	private World world;
	private int affected = -1; //TODO: FIX it
	
	public SimpleJob(Point min, Point max, World world, JobStateChangedCallback callback, Criteria criteria, Provider provider)
	{
		jobState = JobState.Unstarted;
		this.min = min;
		this.max = max;
		this.world = world;
		this.callback = callback;
		this.criteria = criteria;
		this.provider = provider;
		
		currX = min.getX();
		currY = max.getY();
		currZ = min.getZ();
		
		currLoc = new Point(currX, currY, currZ);
	}

	public Point nextMatchingPosition() {
		Block block = world.getBlockAt(currX, currY, currZ);
		Point loc = getLocationToChange();
		
		BlockData current = new BlockData(block.getType(), block.getData());
		while (!criteria.matches(current, loc) && this.jobState == JobState.Running)
		{
			block = world.getBlockAt(currX, currY, currZ);
			loc = getLocationToChange();
			current = new BlockData(block.getType(), block.getData());
		}
		this.affected++;
		return loc;
	}
	
	private Point getLocationToChange() {
		if (currX == max.getX()+1) {
			this.setState(JobState.Finished);
		}
		currLoc.setX(currX);
		currLoc.setY(currY);
		currLoc.setZ(currZ);
		
		currZ++;
		
		if (currZ == max.getZ()+1) {
			currZ = min.getZ();
			currY--;
		}
		
		if (currY == min.getY()-1) {
			currY = max.getY();
			currX++;
		}
		return this.currLoc;
	}

	public JobState getState() {
		return this.jobState;
	}

	public void setState(JobState state) {
		JobState from = this.jobState;
		this.jobState = state;
		this.callback.jobStateChanged(this, from);
	}

	public Point getMin() {
		return this.min;
	}

	public Point getMax() {
		return this.max;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public BlockData getBlockData() {
		return this.provider.getBlockData(this.currLoc);
	}

	public World getWorld() {
		return world;
	}

	public int getAffectedBlocks() {
		return affected;
	}
}
