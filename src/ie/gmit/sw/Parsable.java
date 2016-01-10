package ie.gmit.sw;
import java.util.*;

public interface Parsable
{
	public void parseFile(String filename) throws Exception;
	public Map<String, Integer> getMap();
}