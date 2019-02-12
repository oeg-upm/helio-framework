package helio.framework;

import helio.framework.exceptions.NotReachableEndpointException;

/**
 * Connector is meant to access to a remote data provider
 * 
 * @author Andrea Cimmino
 *
 */
public interface Connector {

	/**
	 * This method retrieves the content of a remote data provider, e.g., a REST API, a file, a SPARQL database
	 * @return A {@link String} with the data retrieved within
	 * @exception NotReachableEndpointException
	 */
	public String retrieveData() throws NotReachableEndpointException;

}
