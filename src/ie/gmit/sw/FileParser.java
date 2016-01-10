package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FileParser implements Parsable
{
	private Map<String, Integer> wordMap;
	private StopWords s;
	
	public FileParser(StopWords s)
	{
		wordMap = new HashMap<String, Integer>();
		this.s = s;
	}
	public void parseFile(String filename) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		StringBuffer sb = new StringBuffer();
		
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
				if (!s.hasWord(word)&& word.length() > 1) 
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