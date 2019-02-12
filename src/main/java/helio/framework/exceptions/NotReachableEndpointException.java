package helio.framework.exceptions;

public class NotReachableEndpointException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 744492951417055476L;

	public NotReachableEndpointException(String connectorClass, String source) { 
		super("Endpoint "+source+" not reachable using "+connectorClass); 
	}

}
