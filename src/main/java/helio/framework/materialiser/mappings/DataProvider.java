package helio.framework.materialiser.mappings;

import java.io.InputStream;
import java.io.Serializable;

import com.google.gson.JsonObject;

/**
 * This class is meant to be implemented by objects that will provide pieces of data regardless their format, specifically, these implementations are meant to deal with the protocols and methods required to fetch the data.
 * @author Andrea Cimmino
 *
 */
public interface DataProvider extends Serializable {

	/**
	 * This method fetches the data from a sources and provides an {@link InputStream} for consuming it
	 * @return an {@link InputStream} for where the provided data can be read 
	 */
	public InputStream getData();
	
	/**
	 * This method is expected to receive a {@link JsonObject} with the necessary information to setup an implementation of this interface.<p> Check the documentation of the implementations to know more about the different configurations required by those implementations.
	 * @param configuration a suitable configuration
	 */
	public void configure(JsonObject configuration);
}
