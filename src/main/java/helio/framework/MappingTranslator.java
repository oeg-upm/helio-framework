package helio.framework;

import helio.framework.exceptions.MalformedMappingException;
import helio.framework.mapping.Mapping;

/**
 * MappingTranslator is meant to create {@link Mapping} objects using different mapping specifications or formats
 * 
 * @author Andrea Cimmino
 *
 */
public interface MappingTranslator {

	/**
	 * This method receives a plain representation of a mapping, for instance a JSON document, and returns a {@link Mapping}
	 * @param value A plain representation of the mapping
	 * @return A {@link Mapping} initialized with the input plain representation
	 * @throws MalformedMappingException
	 */
	public Mapping translate(String value) throws MalformedMappingException;
}
