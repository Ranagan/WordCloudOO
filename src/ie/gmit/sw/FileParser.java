package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FileParser implements Parsable
{
	private Map<String, Integer> wordMap;
	private StopWords stopWords;
	
	public FileParser(StopWords sw)
	{
		wordMap = new HashMap<String, Integer>();
		this.stopWords = sw;
	}
	public void parseFile(String filename) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		StringBuffer sb = new StringBuffer();
		
		//Adapted from DS assignment.
		int j;
		// End of file while loop
		while((j = br.read()) != -1){
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z' || next == '\'')
			{
				sb.append(next);
			}	
			else 
			{
				String word = sb.toString().toUpperCase();
				sb = new StringBuffer();
				
				// Checks if the word is a stop word
				// if not, adds them to map
				if (!stopWords.hasWord(word)&& word.length() > 3) 
				{
					int frequency = 0;
					if(wordMap.containsKey(word))
					{
						frequency = wordMap.get(word);
					}
					frequency++;
					wordMap.put(word, frequency);
				}
			}
		}
		br.close();		
	}
	public Map<String,Integer> getMap()
	{
		return wordMap;
	}
}