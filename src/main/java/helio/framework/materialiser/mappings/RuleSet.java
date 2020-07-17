package helio.framework.materialiser.mappings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RuleSet {
	
	// -- Attributes
	
	private String resourceRuleId; // An Id that identifies this object
	private EvaluableExpression subjectTemplate; // if contains '{...}' then is a template otherwise is constant
	private List<Rule> properties; 
	private Set<String> datasourcesId; 
	
	// -- Constructor
	
	public RuleSet() {
		super();
		properties = new ArrayList<>();
		datasourcesId = new HashSet<>();
	}
	
	// -- Getters & Setters

	public Boolean hasDataSourceId(String dataSourceId) {
		return datasourcesId.contains(dataSourceId);
	}
	
	public String getResourceRuleId() {
		return resourceRuleId;
	}

	public void setResourceRuleId(String resourceRuleId) {
		this.resourceRuleId = resourceRuleId;
	}

	public EvaluableExpression getSubjectTemplate() {
		return subjectTemplate;
	}

	public void setSubjectTemplate(EvaluableExpression subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}

	public List<Rule> getProperties() {
		return properties;
	}

	public void setProperties(List<Rule> properties) {
		this.properties = properties;
	}
	
	public Set<String> getDatasourcesId() {
		return datasourcesId;
	}

	public void setDatasourcesId(Set<String> datasourcesId) {
		this.datasourcesId = datasourcesId;
	}
	
	// -- Other methods
	

	@Override
	public String toString() {
		return "Rule id " + resourceRuleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resourceRuleId == null) ? 0 : resourceRuleId.hashCode());
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
		RuleSet other = (RuleSet) obj;
		if (resourceRuleId == null) {
			if (other.resourceRuleId != null)
				return false;
		} else if (!resourceRuleId.equals(other.resourceRuleId))
			return false;
		return true;
	}


	
	
	
	
	
}
