package de.pro_crafting.generator.job;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.bukkit.World;
import org.bukkit.block.Block;

import de.pro_crafting.common.Point;
import de.pro_crafting.common.Size;
import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.JobState;
import de.pro_crafting.generator.JobStateChangedCallback;
import de.pro_crafting.generator.provider.Provider;
import de.pro_crafting.generator.provider.SizeProvider;

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
	private Point origin;
	private Size size;
	
	public SimpleJob(Point origin, World world, JobStateChangedCallback callback, SizeProvider provider) {
		this(origin, provider.getSize(), world, callback, provider);
	}

	public SimpleJob(Point origin, Size size, World world, JobStateChangedCallback callback, Provider provider) {
		this.jobState = JobState.Unstarted;
		this.world = world;
		this.callback = callback;
		this.provider = provider;
		
		this.origin = origin;
		this.size = size;
		
		this.currX = 0;
		this.currY = 0;
		this.currZ = 0;
		
		
		this.affected = 0;
		currLoc = new Point(currX, currY, currZ);
	}
	
	public Entry<Point, BlockData> next() {
		Point relativeLocation = getLocationToChange();
		Point worldLocation = new Point(relativeLocation.getX()+this.origin.getX(), relativeLocation.getY()+this.origin.getY(), relativeLocation.getZ()+this.origin.getZ());
		Block block = world.getBlockAt(worldLocation.getX(), worldLocation.getY(), worldLocation.getZ());
		
		BlockData current = new BlockData(block.getType(), block.getData());
		BlockData ret = this.provider.getBlockData(relativeLocation, current);
		
		if (!current.equals(ret)) {
			affected++;
		}
		
		return new SimpleEntry<Point, BlockData>(worldLocation, ret);
	}
	
	private Point getLocationToChange() {
		if (currX == getSize().getWidth()+1) {
			this.setState(JobState.Finished);
			return currLoc;
		}
		currLoc.setX(currX);
		currLoc.setY(currY);
		currLoc.setZ(currZ);
		
		currZ++;
		
		if (currZ == getSize().getDepth()+1) {
			currZ = 0;
			currY--;
		}
		
		if (currY == 0) {
			currY = getSize().getHeight();
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

	public Point getOrigin() {
		return this.origin;
	}

	public Size getSize() {
		return this.size;
	}
	
	public World getWorld() {
		return this.world;
	}

	public int getAffectedBlocks() {
		return this.affected;
	}
}
