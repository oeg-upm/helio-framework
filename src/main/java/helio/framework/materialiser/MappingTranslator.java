package helio.framework.materialiser;

import helio.framework.exceptions.MalformedMappingException;
import helio.framework.materialiser.mappings.HelioMaterialiserMapping;

/**
 * MappingTranslator is meant to create {@link Mapping} objects using different mapping specifications or formats
 * 
 * @author Andrea Cimmino
 *
 */
public interface MappingTranslator {

	/**
	 * This method receives a plain representation of a mapping, for instance a JSON document, and returns a {@link Mapping}
	 * @param mappingContent A plain representation of the mapping
	 * @return A {@link Mapping} initialized with the input plain representation
	 * @throws MalformedMappingException
	 */
	public HelioMaterialiserMapping translate(String mappingContent) throws MalformedMappingException;
	
	/**
	 * This method receives a plan representation of a mapping and returns a boolean value whether such mapping is compatible with the translator
	 * @param mappingContent A plain representation of the mapping
	 * @return A boolean value specifiying if the mapping is compatible with the translator, i.e., it can be processed
	 */
	public Boolean isCompatible(String mappingContent);
}
