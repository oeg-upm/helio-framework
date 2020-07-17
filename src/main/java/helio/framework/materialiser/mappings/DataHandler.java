package helio.framework.materialiser.mappings;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Queue;

public interface DataHandler extends Serializable{

	public Queue<String> splitData(InputStream dataStream);
	public String filter(String filter, String dataChunk); 
	
}
