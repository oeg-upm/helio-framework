package helio.framework;

import java.util.List;

import helio.framework.exceptions.DataNotProcessableException;
import helio.framework.exceptions.LiteralNotFounException;
import helio.framework.exceptions.NotReachableEndpointException;

/**
 * Datasource is meant to interact with data retrieved by a {@link Connector} from a remote data provider
 * 
 * @author Andrea Cimmino
 *
 */
public interface Datasource {

	/**
	 * This method returns the data read from a remote data provider by means of a {@link Connector}. 
	 * <p>
	 * The data is processed so each fragment of data that corresponds to a virtual resource is separated from the rest of data
	 * @return A {@link List} of {@link String} in which each element is the data corresponding to a virtual resource
	 * @throws DataNotProcessableException, NotReachableEndpointException
	 */
	public List<String> readData() throws DataNotProcessableException, NotReachableEndpointException;

	/**
	 * This method applies a filtering expression to the data in order to retrieve a specific {@link String} value 
	 * @param data The content where the filtering expression will be applied to retrieve a specific value
	 * @param filter A filtering expression
	 * @return A {@link String} specific value from the input data following the input filter 
	 * @throws LiteralNotFounException
	 */
	public List<String> accessData(String data, String filter) throws LiteralNotFounException;
}
