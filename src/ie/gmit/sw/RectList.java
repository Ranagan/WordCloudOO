package ie.gmit.sw;

import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

public class RectList 
{
private Set<Rectangle2D> rectList;
	
	public RectList()
	{
		//Create a list of rectangles, where the words will be displayed
		rectList = new HashSet<Rectangle2D>();
	}
	public void add (Rectangle2D rect)
	{
		//Add a new rect
		rectList.add(rect);
	}
	public void remove(Rectangle2D rect)
	{
		//Remove a rect
		rectList.remove(rect);
	}
	public boolean checkOverLap(Rectangle2D rect)
	{
		boolean overlapping = false;
		//Checks to see if new rect is overlapping with any other rect on the list.
		//Has to run through every other rect on the list.
		for(Rectangle2D rectOne : rectList)
		{
			overlapping = (rectOne.intersects(rect));
			
			if(overlapping)
			{
				break;
			}
		}
		
		return overlapping; 
	}

}
