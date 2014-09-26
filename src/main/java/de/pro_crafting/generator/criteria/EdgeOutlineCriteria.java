package de.pro_crafting.generator.criteria;

import de.pro_crafting.common.Point;
import de.pro_crafting.generator.BlockData;

public class EdgeOutlineCriteria implements Criteria {

	private Criteria wraped;
	private Point min;
	private Point max;
	
	public EdgeOutlineCriteria(Point min, Point max)
	{
		this.min = min;
		this.max = max;
	}
	
	public boolean matches(Point point, BlockData block) {
		boolean shouldset = isSide(point, min.getY()) || isSide(point, max.getY()) || isEdge(point);
		
		return wraped != null && shouldset ? wraped.matches(point, block) : shouldset;
	}
	
	private boolean isSide(Point p, int y)
	{
		if (p.getY() == y)
		{
			return p.getX() == min.getX() || p.getZ() == min.getZ() || p.getX() == max.getX() || p.getZ() == max.getZ();
		}
		return false;
	}
	
	private boolean isEdge(Point p)
	{
		return p.getX() == min.getX() && p.getZ() == min.getZ() || 
				p.getX() == min.getX() && p.getZ() == max.getZ() ||
				p.getX() == max.getX() && p.getZ() == min.getZ() ||
				p.getX() == max.getX() && p.getZ() == max.getZ();
	}
	
	public void wrap(Criteria criteria) {
		if (wraped == null) 
		{
			wraped = criteria;
		}
		else 
		{
			wraped.wrap(criteria);
		}
	}

}
