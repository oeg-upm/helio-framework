package helio.framework.materialiser;

import java.io.InputStream;
import java.io.PipedInputStream;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.rio.RDFFormat;

import helio.framework.objects.SparqlResultsFormat;

public interface MaterialiserCache {

	void addGraph(String namedGraph, String rdf, RDFFormat format);

	void addGraph(String namedGraph, InputStream inputDataStream, RDFFormat format);

	Model getGraph(String namedGraph);

	Model getGraphs();

	Model getGraphs(String... namedGraphs);

	void deleteGraph(String namedGraph);

	PipedInputStream solveTupleQuery(String query, SparqlResultsFormat format);

	PipedInputStream solveGraphQuery(String query, SparqlResultsFormat format);

	void deleteGraphs();
	
	void changeRepository(Repository repository);
}