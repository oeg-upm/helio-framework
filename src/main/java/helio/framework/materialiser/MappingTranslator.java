package helio.framework.materialiser;

import helio.framework.exceptions.MalformedMappingException;
import helio.framework.materialiser.mappings.HelioMaterialiserMapping;

/**
 * MappingTranslator is meant to create {@link HelioMaterialiserMapping} objects using different mapping specifications or formats
 * 
 * @author Andrea Cimmino
 *
 */
public interface MappingTranslator {

	/**
	 * This method receives a plain representation of a mapping, for instance a Json document, and returns a {@link HelioMaterialiserMapping}
	 * @param mappingContent a plain representation of the mapping
	 * @return a {@link HelioMaterialiserMapping} initialized with the input plain representation
	 * @throws MalformedMappingException is thrown when a syntax error is detected in the provided mapping
	 */
	public HelioMaterialiserMapping translate(String mappingContent) throws MalformedMappingException;
	
	/**
	 * This method receives a plain representation of a mapping and returns a boolean value whether such mapping is compatible with the translator or not
	 * @param mappingContent a plain representation of the mapping
	 * @return a boolean value specifying if the mapping is compatible with the translator, i.e., it can be processed
	 */
	public Boolean isCompatible(String mappingContent);
}
