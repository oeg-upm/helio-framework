package helio.orchestrator;

import helio.framework.objects.RDF;

public interface HelioRepository {

	public void updateGraph(String graphURL, RDF data);
	public String getGraph(String graphURL, String format);
	public String getGraph(String format);
	public void clearGraph(String graphURL);
	
	public String solveSelectSPARQL(String query);
	public String solveAskSPARQL(String query);
	public String solveConstructSPARQL(String query);
	public String solveDescribeSPARQL(String query);
	
	public String solveDeleteSPARQL(String query);
	public String solveInsertSPARQL(String query);
	public String solveUpdateSPARQL(String query);
}
