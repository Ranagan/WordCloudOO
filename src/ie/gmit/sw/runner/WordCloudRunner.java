package ie.gmit.sw.runner;
import ie.gmit.sw.*;

public class WordCloudRunner {
	
	private static boolean isFile;
	private static String fileName;
	private static String sw;
	public static void main(String[] args) throws Exception 
	{
		
		fileName = args[0];
		sw = args[1];
		
		if(args[2].equalsIgnoreCase("false"))
		{
			isFile = false;
		}
		else
		{
			isFile = true;
		}
		
		ImageGenerator img;
		StopWords stopWords = new StopWords(sw);
		WordCloudMap map = new WordCloudMap(fileName, stopWords,isFile);
		
		img = new ImageGenerator(map.getMap(), 200);
		System.out.println("Drawing Image...");
		img.drawImage();
		
		System.out.println("Done");
	}

}
