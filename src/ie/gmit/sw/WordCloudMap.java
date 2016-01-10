package ie.gmit.sw;

import java.util.*;

public class WordCloudMap {

	private Map<String, Integer> wordMap = new HashMap<String, Integer>();
	private Parsable parse;
	private String file;

	public WordCloudMap(String fileName, StopWords sw, boolean isfile) throws Exception
	{
		file = fileName;
		
		try
		{
			//Checks to see if it is a file or a URL
			if(isfile)
			{
				parse = new FileParser(sw);
			}
			//If URL
			else
			{
				//parse = new UrlParser(sw);
			}
			createMap();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void createMap() throws Exception
	{
		parse.parseFile(file);

		//Adds the words to the map
		wordMap = parse.getMap();
	}
	public Map<String, Integer> getMap()
	{
		return wordMap;
	}

}
