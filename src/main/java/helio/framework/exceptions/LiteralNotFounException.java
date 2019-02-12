package helio.framework.exceptions;

public class LiteralNotFounException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2509481699905293264L;

	public LiteralNotFounException(String datasourceClass, String reference, String data) { 
		super(" Literal in raw data referenced by "+reference+" not found using "+datasourceClass+" using data "+data); 
	}
}
