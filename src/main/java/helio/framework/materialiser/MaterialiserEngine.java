package helio.framework.materialiser;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;

import helio.framework.exceptions.ResourceNotFoundException;
import helio.framework.objects.SparqlResultsFormat;

/**
 * MaterialiserEngine is meant to translate the data from a set of data sources into RDF, providing access to generated resources, or allowing to perform SPARQL queries.
 * <p>
 * In addition to deal with large RDF data sets, the MaterialiserEngine provides {@link InputStream} for consuming the query answer
 * 
 * @author Andrea Cimmino
 *
 */
public interface MaterialiserEngine {

	/**
	 * This method returns all the triples of the last updated RDF which subject is the one provided
	 * @param iri an IRI that identifies a specific resource, i.e., the subject
	 * @return a {@link Model} containing the relevant triples
	 * @throws ResourceNotFoundException is thrown when the requested IRI was not found
	 */
	public Model getResource(String iri) throws ResourceNotFoundException;
	
	/**
	 * This method returns all the triples of the last updated RDF 
	 * @return a {@link Model} containing the relevant triples
	 */
	public Model getRDF();
	

	/**
	 * This method solves the provided SPARQL over the last updated RDF
	 * @param sparqlQuery a valid SPARQL query (SELECT, ASK, DESCRIBE, or CONSTRUCT)
	 * @param format specifies a {@link SparqlResultsFormat} format in which the query answer will be expressed
	 * @return a {@link String} containing the query answers
	 */
	public String query(String sparqlQuery, SparqlResultsFormat format);
	
	
	/**
	 * This method updates the RDF of all the synchronous {@link helio.framework.materialiser.mappings.DataSource}, if the internal cache is an external triple store the updated triples will be injected in such store
	 */
	public void updateSynchronousSources();
	
	
	/**
	 * This method closes all the background processes of Helio, for instance, the processes that generate the RDF of asynchronous {@link helio.framework.materialiser.mappings.DataSource}
	 */
	public void close();
}
