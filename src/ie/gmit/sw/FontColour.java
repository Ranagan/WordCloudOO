package ie.gmit.sw;

import java.awt.*;
import java.util.Random;

public class FontColour 
{
	private Font font;
	private int fontSize;
	private Color fontColour;
	
	public void setFont(String name, int style, int size)
	{
		font = new Font(name, style, size);
	}

	public void setFont() 
	{
		setFont(randomFont(), Font.PLAIN, getSize());		
	}
	public void setColour()
	{
		fontColour = getRandomColor();
	}
	public Color getColour()
	{
		return fontColour;
	}
	public void setSize(int size)
	{
		//Bigger words are given a smaller font to fit on the image
		if(size>72)
		{
			size = 72;
		}
		if(size>120)
		{
			size = 100;
		}
		if(size > 500)
		{
			size = 200;
		}
		if(size>1000)
		{
			size = 300;
		}
		fontSize = (int)(size);
		
	}
	
	public int getSize()
	{
		return fontSize;
	}
	
	public Font getFont()
	{
		return font;
	}

	private Color getRandomColor()
	{		
		//Randomizes the colour of the font for the word cloud
		return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
	}
	
	private String randomFont()
	{
		Random random = new Random();
		//Random font style
		switch(random.nextInt(3))
		{
		case 0:
			return Font.SANS_SERIF;
			
		case 1:
			return Font.MONOSPACED;
			
		case 2:
			return Font.SERIF;
			
		default:
			return Font.SANS_SERIF;
		}		
	}
}
