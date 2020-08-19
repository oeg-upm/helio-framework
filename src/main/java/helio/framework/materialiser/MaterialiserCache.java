package helio.framework.materialiser;

import java.io.InputStream;
import java.io.PipedInputStream;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.rio.RDFFormat;

import helio.framework.objects.SparqlResultsFormat;

/**
 * The MaterialiserCache is meant to be implemented by classes that allow the {@link MaterialiserEngine} to store copies of the generated RDF.<p>
 *
 * @author Andrea Cimmino
 *
 */
public interface MaterialiserCache {

	/**
	 * This method adds an RDF graph in the cache
	 * @param namedGraph the name of the graph
	 * @param rdf the piece of RDF to be stored
	 * @param format the format of the provided piece of RDF
	 */
	void addGraph(String namedGraph, String rdf, RDFFormat format);

	/**
	 * This method adds an RDF graph in the cache
	 * @param namedGraph the name of the graph
	 * @param rdf the piece of RDF to be stored
	 * @param format the format of the provided piece of RDF
	 */
	void addGraph(String namedGraph, InputStream inputDataStream, RDFFormat format);

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
	 * @return a stream with the query answers
	 */
	PipedInputStream solveTupleQuery(String query, SparqlResultsFormat format);

	/**
	 * This method solves a CONSTRUCT or DESCRIBE SPARQL query over the RDF stored in the cache
	 * @param query a SPARQL 1.1 valid query
	 * @param format the query answering output format
	 * @return a stream with the query answers
	 */
	PipedInputStream solveGraphQuery(String query, SparqlResultsFormat format);

	/**
	 * This method erases all the named RDF graphs within the cache
	 */
	void deleteGraphs();
	
	/**
	 * This method allows modifying the internal data handler with a different {@link Repository}
	 * @param repository a valid {@link Repository} that will handle the data to be stored in the cache
	 */
	void changeRepository(Repository repository);
}