package helio.framework.materialiser.mappings;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Queue;

import com.google.gson.JsonObject;

/**
 * This class is meant to be implemented by objects that will handle pieces of data in a specific format, allowing the {@link helio.framework.materialiser.MaterialiserEngine} to split data, and access specific pieces, in order to generate a final RDF document
 * @author Andrea Cimmino
 *
 */
public interface DataHandler extends Serializable{

	/**
	 * This method split the provided data into chunks using some iterator, like a Json Path or an XPat (usually provided as part of the configuration)
	 * @param dataStream A stream containing the data to be translated into RDF, that has a specific format that this Data Handler knows how to manage
	 * @return a {@link Queue} in which each element corresponds to a chunk of data
	 */
	public Queue<String> splitData(InputStream dataStream);
	
	/**
	 * This method must select one or more elements from a chunk of data, using a provided filter (like a Json Path or a XPat)
	 * @param filter the filtering expression
	 * @param dataChunk the piece of data from which extract the information using the filtering expression
	 * @return all the occurrences found in the piece of data using the filtering expression
	 */
	public List<String> filter(String filter, String dataChunk); 
	public void configure(JsonObject configuration);

}
