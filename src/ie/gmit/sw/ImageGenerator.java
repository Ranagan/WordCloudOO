package ie.gmit.sw;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class ImageGenerator
{
	private Map<String, Integer> map;
	private int maxWords;
	private RectList rectList;
	private Graphics2D graphics;
	private FontColour font;
	private int xposition;
	private int yposition ;
	private int direction =0;
	private int i;
	
	public ImageGenerator(Map<String, Integer> map, int max) throws Exception
	{
		this.maxWords = max;
		this.map = map;
		font = new FontColour();
		rectList = new RectList();
		xposition = 1920/2;
		yposition = 1080/2;
		i =0;
	}
	public void drawImage() throws Exception
	{
		BufferedImage image = new BufferedImage(1920, 1080, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = (Graphics2D)image.getGraphics();
		i = 0;
		//runs through each word in the set add draws it
		for(String word : map.keySet())
		{  	
			//determines what direction the word will be moved
			changeDirection();
			
			if(map.get(word) <= 1 == false)
			{
				//sets up words colour, size and font
				font.setSize(map.get(word) + 15);
				font.setFont();
				font.setColour();
				//draws word
				drawWord(font.getFont(), font.getColour(),word, direction);
			}
			if(i == maxWords)
			{
				//exits for loop if max amount of words is hit
				break;
			}
			i++;
		}
		graphics.dispose();
		//creates the actual image file
		ImageIO.write(image, "png", new File("image.png"));
	}
	private void changeLocation(Rectangle2D r, int direction)
	{
		switch(direction)
		{
		//alters x and y positions based on direction
			case 0:
				if(!(r.getY() < 0))
					xposition-=3;				
				else
					resetPosition();
				break;
				
			case 1:
				if(!(r.getX() + r.getWidth() > 1500))		
					yposition+=3;				
				else
					resetPosition();
				break;
				
			case 2:
				if(!(r.getY() + r.getHeight() > 750))		
					xposition+=3;				
				else
					resetPosition();
				break;
				
			case 3:
				if(!(r.getX() < 0))				
					yposition-=3;				
				else
					resetPosition();
				break;	
		}	
	}
	private void resetPosition()
	{
		//resets word back to initial position
		xposition = 1920/2;
		yposition = 1080/2;
		changeDirection();
	}
	private void changeDirection()
	{
		if(direction == 3)
		{
			direction = 0;
		}		
		else
		{
			direction++;
		}
	}
	private Rectangle getBoundaries(Graphics2D g, String word, int x, int y)
	{
		//gets boundaries of string so that a rectangle can be placed around it
		FontRenderContext frc = g.getFontRenderContext();
		GlyphVector gv = g.getFont().createGlyphVector(frc, word);
		return gv.getPixelBounds(null, x, y);
	}
	private void drawWord(Font font, Color color, String word, int direction)
	{	
		Rectangle2D rect ;
		
		graphics.setFont(font);
		graphics.setColor(color);
		
		rect = getBoundaries(graphics, word, xposition, yposition); 
        //continues to check if the rectangle is overlapping with another on the list
		//and will continue to move it until it is not
		while(rectList.checkOverLap(rect))
		{
			changeLocation(rect, direction);
			rect = getBoundaries(graphics, word, xposition, yposition); 
		}
		//adds the rectangle to the list meaning future words won't collide with it
		rectList.add(rect);
		//draws the word
		graphics.drawString(word, xposition, yposition);
	}
}