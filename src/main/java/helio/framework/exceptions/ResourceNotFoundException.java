package helio.framework.exceptions;

public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = -5273900671557139462L;

	public ResourceNotFoundException(String iri) { 
		super("Requested resource was not found, check its IRI: "+iri); 
	}

}
