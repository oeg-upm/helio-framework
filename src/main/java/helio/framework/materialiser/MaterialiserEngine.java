package helio.framework.materialiser;

import java.io.PipedInputStream;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import helio.framework.exceptions.ResourceNotFoundException;
import helio.framework.objects.SparqlResultsFormat;

/**
 * MaterialiserEngine is meant to translate the data from a  set of data provider into RDF, or providing access to existing resources or allowing to perform SPARQL queries.
 * <p>
 * In addition to deal with large RDF data sets, the MaterialiserEngine provides {@link PipedInputStream} for consuming the RDF data
 * 
 * @author Andrea Cimmino
 *
 */
public interface MaterialiserEngine {

	/**
	 * This method retrieves the {@link RDF} of the provided IRI
	 * @param iri An IRI that identifies a specific resource
	 * @return All the {@link RDF} data of such resource
	 * @throws ResourceNotFoundException
	 */
	public Model getResource(String iri, RDFFormat format) throws ResourceNotFoundException;
	
	/**
	 * This method returns all the {@link RDF} translated from the data of a provider
	 * @return All the {@link RDF}
	 */
	public Model getRDF(RDFFormat format);
	

	/**
	 * This method returns a {@link JSONArray} containing several {@link JSONObject} each of which is a solution to the input query
	 * @param sparqlQuery A SPARQL query
	 * @param SparqlAnswerFormat specifies the format in which the query answer will be expressed. To know more about the the possible values check {@link ResultsFormat}
	 * @return A {@link JSONArray} containing all the query solutions found
	 */
	public String query(String sparqlQuery, SparqlResultsFormat format);
	
	
	/**
	 * This method returns a {@link JSONArray} containing several {@link JSONObject} each of which is a solution to the input query
	 * @param sparqlQuery A SPARQL query
	 * @param SparqlAnswerFormat specifies the format in which the query answer will be expressed. To know more about the the possible values check {@link ResultsFormat}
	 * @return A {@link JSONArray} containing all the query solutions found
	 */
	public PipedInputStream queryStream(String sparqlQuery, SparqlResultsFormat format);
	
	
	/**
	 * This method updates the RDF translated from those sources configured as synchronous. If using a persistent triplestore this method updates its content without performing any operation over the produced RDF
	 */
	public void updateSynchronousSources();
	
	
	public void close();
}
