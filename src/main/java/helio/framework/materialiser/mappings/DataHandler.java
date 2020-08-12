package helio.framework.materialiser.mappings;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Queue;

import com.google.gson.JsonObject;

public interface DataHandler extends Serializable{

	public Queue<String> splitData(InputStream dataStream);
	public String filter(String filter, String dataChunk); 
	public void configure(JsonObject configuration);

}
