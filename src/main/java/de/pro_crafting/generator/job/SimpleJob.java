package de.pro_crafting.generator.job;

import org.bukkit.World;
import org.bukkit.block.Block;

import de.pro_crafting.common.Point;
import de.pro_crafting.common.Size;
import de.pro_crafting.generator.BlockData;
import de.pro_crafting.generator.JobState;
import de.pro_crafting.generator.JobStateChangedCallback;
import de.pro_crafting.generator.provider.Provider;
import de.pro_crafting.generator.provider.SizeProvider;

public class SimpleJob implements Job {
	private Point relativeLocation;
	
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
		
		relativeLocation = new Point(0, size.getHeight(), 0);
	}
	
	public boolean next() {
		Point worldLocation = new Point(relativeLocation.getX()+this.origin.getX(), relativeLocation.getY()+this.origin.getY(), relativeLocation.getZ()+this.origin.getZ());
		Block block = world.getBlockAt(worldLocation.getX(), worldLocation.getY(), worldLocation.getZ());
		
		BlockData current = new BlockData(block.getType(), block.getData());
		BlockData ret = this.provider.getBlockData(relativeLocation, current);
		
		nextPosition();
		if (!apply(block, ret)) {
			affected++;	
			return true;
		}
		return false;
	}
	
	private boolean apply(Block currentBlock, BlockData data) {
		if (data == null) {
			return false;
		}
		return currentBlock.setTypeIdAndData(data.getType().getId(), data.getDataByte(), true);
	}
	
	private void nextPosition() {
		if (relativeLocation.getX() == getSize().getWidth()+1) {
			this.setState(JobState.Finished);
		}

		relativeLocation.setZ(relativeLocation.getZ()+1);
		
		if (relativeLocation.getZ() == getSize().getDepth()+1) {
			relativeLocation.setZ(0);
			relativeLocation.setY(relativeLocation.getY()-1);
		}
		
		if (relativeLocation.getY() == -1) {
			relativeLocation.setY(getSize().getHeight());
			relativeLocation.setX(relativeLocation.getX()+1);
		}
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
