package helio.framework.exceptions;

public class MalformedMappingException extends Exception {

	private static final long serialVersionUID = -6818771741212696549L;

	public MalformedMappingException(String mappingError) { 
		super("Mapping has errors: "+mappingError); 
	}

}
