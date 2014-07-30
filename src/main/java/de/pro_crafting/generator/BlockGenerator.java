package de.pro_crafting.generator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.job.Job;

public class BlockGenerator 
{
	private JavaPlugin plugin;
	private List<Job> jobs;
	private BukkitTask task;
	private int maxBlockChange;
	
	public BlockGenerator(JavaPlugin plugin, int maxBlockChange)
	{
		this.plugin = plugin;
		this.jobs = new ArrayList<Job>();
		this.maxBlockChange = maxBlockChange;
	}
	
	private void changeBlocks()
	{
		int changedBlocks = 0;
		Point toChange = null;
		for (int i=jobs.size()-1;i>-1;i--)
		{
			Job job = jobs.get(i);
			if (job.getState() == JobState.Unstarted)
			{
				job.setState(JobState.Running);
			}
			while (changedBlocks<this.maxBlockChange&&job.getState()==JobState.Running)
			{
				toChange = job.nextMatchingPosition();
				if (job.getState() != JobState.Running)
				{
					break;
				}
				apply(job.getWorld(), toChange, job.getBlockData());
				changedBlocks++;
			}
			if (job.getState() == JobState.Finished)
			{
				jobs.remove(i);
			}
			if (changedBlocks>=this.maxBlockChange)
			{
				break;
			}
		}
		if (this.jobs.size() == 0)
		{
			task.cancel();
			task = null;
		}
	}
	
	private void apply(World world, Point loc, BlockData data)
	{
		if (data == null) {
			return;
		}
		Block block = world.getBlockAt(loc.getX(), loc.getY(), loc.getZ());
		block.setType(data.getType());
		block.setData(data.getDataByte());
	}
	
	private void startTask()
	{
		if (task == null)
		{
			task = this.plugin.getServer().getScheduler().runTaskTimer(this.plugin, new Runnable(){
				public void run()
				{
					BlockGenerator.this.changeBlocks();
				}
			}, 0, 1);
		}
	}
	
	public void addJob(Job job)
	{
		job.setState(JobState.Running);
		this.jobs.add(job);
		startTask();
	}
}
