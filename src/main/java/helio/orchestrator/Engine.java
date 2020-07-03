package helio.orchestrator;

import helio.framework.objects.SparqlResultsFormat;

public interface Engine {

	
	public void updateRDF(HelioRepository repository, String query);
	
	public String solveQuery(String query, SparqlResultsFormat format);
	
}
