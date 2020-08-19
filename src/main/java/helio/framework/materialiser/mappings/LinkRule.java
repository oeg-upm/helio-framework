package helio.framework.materialiser.mappings;

/**
 * This object represents a rule to link two RDF resources using a fuzzy similarity expression
 * @author Andrea Cimmino
 *
 */
public class LinkRule {

	
	private String sourceNamedGraph;
	private String targetNamedGraph;
	private EvaluableExpression expression;

	public LinkRule() {
		super();
	}
	
	// Getters & Setters

	public String getSourceNamedGraph() {
		return sourceNamedGraph;
	}
	public void setSourceNamedGraph(String sourceNamedGraph) {
		this.sourceNamedGraph = sourceNamedGraph;
	}
	public String getTargetNamedGraph() {
		return targetNamedGraph;
	}
	public void setTargetNamedGraph(String targetNamedGraph) {
		this.targetNamedGraph = targetNamedGraph;
	}
	public EvaluableExpression getExpression() {
		return expression;
	}
	public void setExpression(EvaluableExpression expression) {
		this.expression = expression;
	}

	// Ancillary methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((sourceNamedGraph == null) ? 0 : sourceNamedGraph.hashCode());
		result = prime * result + ((targetNamedGraph == null) ? 0 : targetNamedGraph.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkRule other = (LinkRule) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (sourceNamedGraph == null) {
			if (other.sourceNamedGraph != null)
				return false;
		} else if (!sourceNamedGraph.equals(other.sourceNamedGraph))
			return false;
		if (targetNamedGraph == null) {
			if (other.targetNamedGraph != null)
				return false;
		} else if (!targetNamedGraph.equals(other.targetNamedGraph))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LinkRule [sourceNamedGraph=" + sourceNamedGraph + ", targetNamedGraph=" + targetNamedGraph
				+ ", expression=" + expression + "]";
	}
	
	
	
	
}
