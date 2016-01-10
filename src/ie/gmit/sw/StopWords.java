package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class StopWords implements Parsable
{
	private HashSet<String> stopWordsSet;
	
	public StopWords(String filename) throws Exception
	{
		//Creates a hashset of common words such as 'and' or 'the' because we
		//don't want these words in the word cloud
		stopWordsSet = new HashSet<String>();
		try
		{
			//try to parse the stop words text file,
			//if it fails, throw an exception
			parseFile(filename);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void parseFile(String filename) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		StringBuffer sb = new StringBuffer();
		
		int i;
		while((i = br.read()) != -1)
		{
			char next = (char)i;
			
			if (next != '\n') sb.append(next);
			
			else 
			{
				//Add word to set
				String sWord = sb.toString().toUpperCase();
				sb = new StringBuffer();	
				stopWordsSet.add(sWord);
			}
		}
		br.close();
	}
	public boolean hasWord(String word)
	{
		// Checks to see if the word is one of the stop words.
		// Used when about to add a word to the word cloud map.
		boolean exists = false;
		if(stopWordsSet.contains(word))
		{
			exists = true;
		}
		return exists;
	}
	
	@Override
	public Map<String, Integer> getMap() {
		// TODO Auto-generated method stub
		return null;
	}
}