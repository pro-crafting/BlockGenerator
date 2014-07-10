package de.pro_crafting.generator.job;

import org.bukkit.World;

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
	private JobState jobState = JobState.Unstarted;
	private Point min;
	private Point max;
	private JobStateChangedCallback callback;
	private Criteria criteria;
	private Provider provider;
	private World world;
	
	public SimpleJob(Point min, Point max, World world, JobStateChangedCallback callback, Criteria criteria, Provider provider)
	{
		jobState = JobState.Unstarted;
		this.min = min;
		this.max = max;
		this.callback = callback;
		this.criteria = criteria;
		this.provider = provider;
		
		this.currX = min.getX();
		this.currY = max.getY();
		this.currZ = min.getZ();
		currLoc = new Point(currX, currY, currZ);
	}

	public Point nextMatchingPosition() {
		Point loc = getLocationToChange();
		while (!criteria.matches(getBlockData()) && this.jobState == JobState.Running)
		{
			loc = getLocationToChange();
		}
		return loc;
	}
	
	public Point getLocationToChange() {
		if (this.currX == max.getX() && this.currY == min.getY() && this.currZ == max.getZ())
		{
			this.setState(JobState.Finished);
		}
		for (;currX<max.getX()+1;currX++)
		{
			for (;currY>min.getY()-1;currY--)
			{
				for (;currZ<max.getZ()+1;)
				{
					currLoc.setX(currX);
					currLoc.setY(currY);
					currLoc.setZ(currZ);
					currZ++;
					return this.currLoc;
				}
				currZ=min.getZ();
			}
			currY=max.getY();
		}
		this.setState(JobState.Finished);
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
		// TODO Auto-generated method stub
		return world;
	}
}
