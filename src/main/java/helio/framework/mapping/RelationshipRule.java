package helio.framework.mapping;

import java.util.ArrayList;
import java.util.List;

public class RelationshipRule {

	private String expression;
	private List<String> predicates;
	private List<String> inversePredicates;
	private String subjectResourceRuleId;
	private String targetResourceRuleId;

	// -- Constructor
	
	public RelationshipRule() {
		super();
		predicates = new ArrayList<>();
		inversePredicates = new ArrayList<>();
	}
	
	// -- Getters & Setters
	
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getSubjectResourceRuleId() {
		return subjectResourceRuleId;
	}

	public void setSubjectResourceRuleId(String subjectResourceRuleId) {
		this.subjectResourceRuleId = subjectResourceRuleId;
	}

	public String getTargetResourceRuleId() {
		return targetResourceRuleId;
	}

	public void setTargetResourceRuleId(String targetResourceRuleId) {
		this.targetResourceRuleId = targetResourceRuleId;
	}

	public List<String> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<String> predicates) {
		this.predicates = predicates;
	}

	public List<String> getInversePredicates() {
		return inversePredicates;
	}

	public void setInversePredicates(List<String> inversePredicates) {
		this.inversePredicates = inversePredicates;
	}

	// -- Other methods
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		predicates.stream().forEach(predicate -> str.append("Relationship <").append(predicate).append("> between ").append(subjectResourceRuleId).append(" and ").append(targetResourceRuleId).append(" under condition: ").append(expression).append("\n"));
		inversePredicates.stream().forEach(predicate -> str.append("Relationship <").append(predicate).append("> between ").append(targetResourceRuleId).append(" and ").append(subjectResourceRuleId).append(" under condition: ").append(expression).append("\n"));
		
		return str.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((inversePredicates == null) ? 0 : inversePredicates.hashCode());
		result = prime * result + ((predicates == null) ? 0 : predicates.hashCode());
		result = prime * result + ((subjectResourceRuleId == null) ? 0 : subjectResourceRuleId.hashCode());
		result = prime * result + ((targetResourceRuleId == null) ? 0 : targetResourceRuleId.hashCode());
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
		RelationshipRule other = (RelationshipRule) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (inversePredicates == null) {
			if (other.inversePredicates != null)
				return false;
		} else if (!inversePredicates.equals(other.inversePredicates))
			return false;
		if (predicates == null) {
			if (other.predicates != null)
				return false;
		} else if (!predicates.equals(other.predicates))
			return false;
		if (subjectResourceRuleId == null) {
			if (other.subjectResourceRuleId != null)
				return false;
		} else if (!subjectResourceRuleId.equals(other.subjectResourceRuleId))
			return false;
		if (targetResourceRuleId == null) {
			if (other.targetResourceRuleId != null)
				return false;
		} else if (!targetResourceRuleId.equals(other.targetResourceRuleId))
			return false;
		return true;
	}



	
	
	
	
	
	


	
	
	
	
	
	
}
