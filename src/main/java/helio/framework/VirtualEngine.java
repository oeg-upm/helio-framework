package helio.framework;

import org.apache.jena.sparql.resultset.ResultsFormat;

import helio.framework.exceptions.ResourceNotFoundException;
import helio.framework.objects.RDF;
import helio.framework.objects.SparqlResultsFormat;

/**
 * VirtualEngine is meant to translate data from a data provider into {@link RDF}, or providing views of an existing {@link RDF} datasets or SPARQL enpoints.
 * <p>
 * VirtualEngine handles the creation of resources (related to a data provider) and relations among such resources. 
 * <p>
 * In addition this class allows to access the {@link RDF} in three different ways, i.e., at resource level providing an IRI, at dataset level, or providing a SPARQL query
 * 
 * @author Andrea Cimmino
 *
 */
public interface VirtualEngine {

	/**
	 * This method retrieves the {@link RDF} of the provided IRI
	 * @param iri An IRI that identifies a specific resource
	 * @return All the {@link RDF} data of such resource
	 * @throws ResourceNotFoundException
	 */
	public String getResource(String iri) throws ResourceNotFoundException;
	
	/**
	 * This method returns all the {@link RDF} translated from the data of a provider
	 * @return All the {@link RDF}
	 */
	public String publishRDF();
	

	/**
	 * This method returns a {@link JSONArray} containing several {@link JSONObject} each of which is a solution to the input query
	 * @param sparqlQuery A SPARQL query
	 * @param SparqlAnswerFormat specifies the format in which the query answer will be expressed. To know more about the the possible values check {@link ResultsFormat}
	 * @return A {@link JSONArray} containing all the query solutions found
	 */
	public String query(String sparqlQuery, SparqlResultsFormat format);
	
}
