package helio.framework.exceptions;

import helio.framework.objects.RDF;

public class LinkingQueryException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1982913540028799856L;

	public LinkingQueryException(String query, RDF data) { 
		super("Linking process failed on data "+data.getRDF()+"\n for query :\n"+query); 
	}
}
