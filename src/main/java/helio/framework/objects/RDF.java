package helio.framework.objects;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.jena.rdf.model.Model;

public interface RDF {

	// Reading methods
	public List<String> getLiteralObjectProperties(String subject, String predicate);

	public void addObjectProperty(String subject, String predicate, String object);

	public void addDataProperty(String subject, String predicate, String object);

	public void addDataPropertyTyped(String subject, String predicate, String object, String dataType);

	public Set<String> getResources();

	public Model getRDF();

	public RDF getResource(String iri);

	public String toString();

	public String toString(String format);

	public void addRDF(Collection<RDF> values);
	
	public void parseRDF(String strRDF);

	public void addRDF(RDF value);
}
