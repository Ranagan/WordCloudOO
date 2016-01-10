package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLParser implements Parsable {
	
	private URL url;
	private Map<String, Integer> wordMap;
	private StopWords stopWords;
	
	public URLParser(StopWords sw)
	{
		wordMap = new HashMap<String, Integer>();
		this.stopWords = sw;
		System.out.println("WordCloudMap finished...");
	}
	public void parseFile(String urlStr) throws Exception
	{
		url = new URL(urlStr);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuffer sb = new StringBuffer();
	    int j;
		// EoF while loop
		while((j = in.read()) != -1){
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z' || next == '\'')
			{
				sb.append(next);
			}	
			else 
			{
				String word = sb.toString().toUpperCase();
				sb = new StringBuffer();
				
				// Checks the list of stop words
				// If word not there, add to map
				if (!stopWords.hasWord(word)&& word.length() > 3) 
				{
					int frequency = 0;
					if(wordMap.containsKey(word))
					{
						frequency = wordMap.get(word);
					}
					frequency++;
					wordMap.put(word, frequency);
					//System.out.println(word + " : " + frequency);
				}
			}
		}
		in.close();			
		
	}
	public Map<String,Integer> getMap()
	{
		return wordMap;
	}
}
