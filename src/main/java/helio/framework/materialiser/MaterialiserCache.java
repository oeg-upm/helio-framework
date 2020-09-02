package helio.framework.materialiser;

import org.apache.jena.rdf.model.Model;
import helio.framework.objects.SparqlResultsFormat;

/**
 * The MaterialiserCache is meant to be implemented by classes that allow the {@link helio.framework.materialiser.MaterialiserEngine} to store copies of the generated RDF.<p>
 *
 * @author Andrea Cimmino
 *
 */
public interface MaterialiserCache {

	
	void addGraph(String namedGraph, Model model);

	/**
	 * This method retrieves a named graph form the cache
	 * @param namedGraph the name of an RDF piece of graph
	 * @return a {@link Model} containing the RDF
	 */
	Model getGraph(String namedGraph);

	/**
	 * This method retrieves all the RDF stored in the cache under a name
	 * @return a {@link Model} containing the stored RDF
	 */
	Model getGraphs();

	/**
	 * This method retrieves a set of named graphs form the cache
	 * @param namedGraphs a set of names belonging to RDF pieces of graphs
	 * @return a {@link Model} containing the RDF
	 */
	Model getGraphs(String... namedGraphs);

	
	/**
	 * This method erases the piece of RDF graph identified by the provided name
	 * @param namedGraph the name of a piece of RDF graph to be deleted
	 */
	void deleteGraph(String namedGraph);

	/**
	 * This method solves a SELECT or ASK SPARQL query over the RDF stored in the cache
	 * @param query a SPARQL 1.1 valid query
	 * @param format the query answering output format
	 * @return a {@link String} containing the query answers
	 */
	String solveTupleQuery(String query, SparqlResultsFormat format);

	/**
	 * This method solves a CONSTRUCT or DESCRIBE SPARQL query over the RDF stored in the cache
	 * @param query a SPARQL 1.1 valid query
	 * @return a stream with the query answers
	 */
	Model solveGraphQuery(String query);

	/**
	 * This method erases all the named RDF graphs within the cache
	 */
	void deleteGraphs();
	
	
	/**
	 * This methods sets up, or modifies, the repository using a configuration file.
	 * @param configuration a configuration expected by a specific {@link MaterialiserCache},  depending on the implementation the content of the variable may change
	 */
	void configureRepository(String configuration);
}