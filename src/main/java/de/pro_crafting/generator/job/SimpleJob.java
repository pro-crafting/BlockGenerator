package de.pro_crafting.generator.job;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.bukkit.World;
import org.bukkit.block.Block;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.JobState;
import de.pro_crafting.generator.JobStateChangedCallback;
import de.pro_crafting.generator.provider.Provider;

public class SimpleJob implements Job
{
	private int currX;
	private int currY;
	private int currZ;
	private Point currLoc;
	private JobState jobState;
	private JobStateChangedCallback callback;
	private Provider provider;
	private World world;
	private int affected;
	
	public SimpleJob(World world, JobStateChangedCallback callback, Provider provider)
	{
		jobState = JobState.Unstarted;
		this.world = world;
		this.callback = callback;
		this.provider = provider;
		
		currX = this.getMin().getX();
		currY = this.getMax().getY();
		currZ = this.getMin().getZ();
		
		currLoc = new Point(currX, currY, currZ);
	}

	public Entry<Point, BlockData> next() {
		Block block = world.getBlockAt(currX, currY, currZ);
		Point loc = getLocationToChange();
		
		BlockData current = new BlockData(block.getType(), block.getData());
		BlockData ret = this.provider.getBlockData(loc, current);
		
		return new SimpleEntry<Point, BlockData>(loc, ret);
	}
	
	private Point getLocationToChange() {
		if (currX == getMax().getX()+1) {
			this.setState(JobState.Finished);
			return currLoc;
		}
		currLoc.setX(currX);
		currLoc.setY(currY);
		currLoc.setZ(currZ);
		
		currZ++;
		
		if (currZ == getMax().getZ()+1) {
			currZ = getMin().getZ();
			currY--;
		}
		
		if (currY == getMin().getY()-1) {
			currY = getMax().getY();
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
		if (this.callback != null) {
			this.callback.jobStateChanged(this, from);
		}
	}

	public Point getMin() {
		return provider.getMin();
	}

	public Point getMax() {
		return provider.getMax();
	}

	public World getWorld() {
		return world;
	}

	public int getAffectedBlocks() {
		return affected;
	}
}
