package helio.framework.objects;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.topbraid.shacl.validation.ValidationUtil;

import com.googlecode.cqengine.persistence.support.serialization.PersistenceConfig;

import helio.framework.objects.RDF;

@PersistenceConfig(polymorphic = true)
public class RDF{

	private Model model;
	
	/**
	 * Constructor of this class<p>
	 * @dataAddress iri A file directory or a URL exposing RDF
	 */
	public RDF(String dataAddress) {
		this.model = ModelFactory.createDefaultModel();
		this.model.read(dataAddress);
	}
	
	/**
	 * Constructor of this class<p>
	 */
	public RDF() {
		this.model = ModelFactory.createDefaultModel();
	}
	
	// Reading methods
	public List<String> getLiteralObjectProperties(String subject, String predicate) {
		List<String> literalObjectProperties =  new ArrayList<>();
		// 1. Retrieve objects from model
		NodeIterator literalObjectPropertiesIterator = null;
		Property property = ResourceFactory.createProperty(predicate);
		if(subject!=null && !subject.isEmpty()) {
			literalObjectPropertiesIterator = model.listObjectsOfProperty(ResourceFactory.createResource(subject), property);
		}else {
			literalObjectPropertiesIterator = model.listObjectsOfProperty(null, property);
		}
		// 2. Filter objects and keep only literals
		while(literalObjectPropertiesIterator.hasNext()) {
			RDFNode literalObjectPropertyNode = literalObjectPropertiesIterator.next();
			if(literalObjectPropertyNode.isLiteral())
				literalObjectProperties.add(literalObjectPropertyNode.asLiteral().getString());
		}
		
		return literalObjectProperties;
	}
	
	// Writing methods
	public void addObjectProperty(String subject, String predicate, String object) {
		Resource subjectResource = ResourceFactory.createResource(subject);
		Property predicateProperty = ResourceFactory.createProperty(predicate);
		Resource objectResource = ResourceFactory.createResource(object);
		if(!model.contains(subjectResource, predicateProperty, objectResource))
			model.add(subjectResource, predicateProperty, objectResource);
	}
	
	
	public void addDataProperty(String subject, String predicate, String object) {
		Resource subjectResource = ResourceFactory.createResource(subject);
		Property predicateProperty = ResourceFactory.createProperty(predicate);
		Literal objectLiteral = ResourceFactory.createTypedLiteral(object);
		if(!model.contains(subjectResource, predicateProperty, objectLiteral))
			model.add(subjectResource, predicateProperty, objectLiteral);
	}
	

	public void addDataPropertyTyped(String subject, String predicate, String object, String dataType) {
		Resource subjectResource = ResourceFactory.createResource(subject);
		Property predicateProperty = ResourceFactory.createProperty(predicate);
		RDFDatatype rdfDataType = new BaseDatatype(dataType);
		Literal objectLiteral = ResourceFactory.createTypedLiteral(object, rdfDataType);
		if(!model.contains(subjectResource, predicateProperty, objectLiteral))
			model.add(subjectResource, predicateProperty, objectLiteral);
	}
	

	// -- Accessing methods
	

	public Set<String> getResources(){
		return model.listResourcesWithProperty(null).toList().stream().map(resource -> resource.getURI()).collect(Collectors.toSet());
	}
	

	public Model getRDF() {
		return this.model;
	}
	

	public RDF getResource(String iri) {
		RDF resourceRDF = new RDF();
		Model resourceModel = ModelFactory.createDefaultModel();
		StmtIterator iterator = model.listStatements(ResourceFactory.createResource(iri), null, (RDFNode) null );
		while(iterator.hasNext()) {
			Statement statement = iterator.next();
			resourceModel.add(statement);
		}
		resourceRDF.getRDF().add(resourceModel);
		return resourceRDF;
	}
	
	
	public String toString() {
		Writer output = new StringWriter();
		model.write(output, "TURTLE");
		return output.toString();
	}
	

	public String toString(String format) {
		Writer output = new StringWriter();
		model.write(output, format);
		return output.toString();
	}
	

	public void addRDF(Collection<RDF> values) {
		values.stream().forEach(this::addRDF);
	}
	

	/**
	 * This method parses and includes in its {@link RDF} object a {@link String} representation of RDF expressed in turtle
	 * @param strRDF a {@link String} representation of RDF expressed in turtle
	 */
	public void parseRDF(String strRDF) {
		 Model parsedModel = ModelFactory.createDefaultModel();
		 InputStream is = new ByteArrayInputStream( strRDF.getBytes() );
		 parsedModel.read(is, null, "TURTLE"); 
		if(this.model!=null) {
			addModel(parsedModel);
		}else {
			this.model = parsedModel;
		}
	}
	
	/**
	 * This method parses and includes in its {@link RDF} object a {@link String} representation of RDF expressed in a given format
	 * @param strRDF a {@link String} representation of RDF
	 * @param strRDF a {@link String} representation of an RDF format, it could be any of: Turtle, RDF/XML, N-Triples, JSON-LD, RDF/JSON, TriG, N-Quads, TriX
	 */
	public void parseRDF(String strRDF, String format) {
		 Model parsedModel = ModelFactory.createDefaultModel();
		 InputStream is = new ByteArrayInputStream( strRDF.getBytes() );
		 parsedModel.read(is, null, format); 
		if(this.model!=null) {
			addModel(parsedModel);
		}else {
			this.model = parsedModel;
		}
	}
	
	public void addRDF(RDF value) {
		if(value!=null)
			addModel(value.getRDF());
		
	}
	
	private void addModel(Model model) {
		StmtIterator statementsIterator = model.listStatements();
		while(statementsIterator.hasNext()) {
			Statement st = statementsIterator.next();
			// Warning when I include a statement s1, p2, o1 using the Model::contains even if I pass the exact same statement it says that is not contained.
			// Alternatively I'm using the following code
			// 1. Check the subject
			if(st.getObject().isLiteral()) {
				NodeIterator nodes = this.model.listObjectsOfProperty(st.getSubject(), st.getPredicate());
				Boolean isContained = false;
				while(nodes.hasNext()) {
					RDFNode node = nodes.next();
					String nodeString = node.toString();
					isContained = nodeString.equals(st.getObject().toString());
					if(isContained)
						break;
				}
				if(!isContained)
					this.model.add(st);
			}else {
				this.model.add(st);
			}
		}
	}

	
	public RDF validateShape(String shapeContent) {
		RDF shape = new RDF();
		shape.parseRDF(shapeContent);
		Resource reportModel = ValidationUtil.validateModel(this.model, shape.getRDF(),false);
		RDF report = new RDF();
		report.getRDF().add(reportModel.getModel());
		return report;
	}
	
}
