package helio.framework;

import helio.framework.objects.HelioResourceRule;
import helio.framework.objects.RDF;

/**
 * Virtualizer is meant to translate data from a provider into {@link RDF}. Virtualizer handles the generation of resources, their data properties, and their object properties
 * <p>
 * 
 * @author Andrea Cimmino
 *
 */
public interface Virtualizer {

	/**
	 * This method output the result of transforming into {@link RDF} the data of a provider
	 * @return All the virtualized {@link RDF}
	 */
	public RDF getVirtualRDF(HelioResourceRule resourceRule, Datasource datasource, Evaluator evaluator, String dataFragment);
	
}
