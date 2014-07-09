package de.pro_crafting.generator.job;

import org.bukkit.Location;

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
	private Location min;
	private Location max;
	private JobStateChangedCallback callback;
	private Criteria criteria;
	private Provider provider;
	
	public SimpleJob(Location min, Location max, JobStateChangedCallback callback, Criteria criteria, Provider provider)
	{
		jobState = JobState.Unstarted;
		this.min = min;
		this.max = max;
		this.callback = callback;
		this.criteria = criteria;
		this.provider = provider;
		
		this.currX = min.getBlockX();
		this.currY = max.getBlockY();
		this.currZ = min.getBlockZ();
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
		if (this.currX == max.getBlockX() && this.currY == min.getBlockY() && this.currZ == max.getBlockZ())
		{
			this.setState(JobState.Finished);
		}
		for (;currX<max.getBlockX()+1;currX++)
		{
			for (;currY>min.getBlockY()-1;currY--)
			{
				for (;currZ<max.getBlockZ()+1;)
				{
					currLoc.setX(currX);
					currLoc.setY(currY);
					currLoc.setZ(currZ);
					currZ++;
					return this.currLoc;
				}
				currZ=min.getBlockZ();
			}
			currY=max.getBlockY();
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

	public Location getMin() {
		return this.min;
	}

	public Location getMax() {
		return this.max;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public BlockData getBlockData() {
		return this.provider.getBlockData(this.currLoc);
	}
}
