package ie.gmit.sw.test;

import org.junit.*;
import ie.gmit.sw.*;

public class WordCloudTestCase 
{
	private WordCloudMap map;
	private StopWords stopwords;
	@Before
	@After
	//What happens when map is null
	public void noMap()
	{
		map = null;
	}
	@Test
	//When it's a valid URL
	public void validUrl() throws Exception
	{
		stopwords = new StopWords("./stopwords.txt");
		map = new WordCloudMap("http://www.gmit.ie/", stopwords, false);
	}
	@Test
	//When the URL given is invalid
	public void inValidURL() throws Exception
	{
		stopwords = new StopWords("./stopwords.txt");
		map = new WordCloudMap("http://ww.gmit.e/", stopwords, false);
	}
	@Test 
	//When it's a valid file
	public void validFile() throws Exception
	{
		stopwords = new StopWords("./stopwords.txt");
		map = new WordCloudMap("./WarAndPeace.txt", stopwords, true);
	}
	@Test
	//When it's an invalid file or file not there.
	public void inValidFile() throws Exception
	{
		stopwords = new StopWords("./stopwords.txt");
		map = new WordCloudMap("./WarPeace.txt", stopwords, true);
	}

}