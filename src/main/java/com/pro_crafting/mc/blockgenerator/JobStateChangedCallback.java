package com.pro_crafting.mc.blockgenerator;

import com.pro_crafting.mc.blockgenerator.job.Job;


public interface JobStateChangedCallback 
{
	void jobStateChanged(Job job, JobState fromState);
}
