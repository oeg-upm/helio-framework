package helio.framework.materialiser.mappings;

import java.util.ArrayList;
import java.util.List;

public class HelioMaterialiserMapping {

	private List<DataSource> datasources;
	private List<RuleSet> ruleSets;
	
	public HelioMaterialiserMapping() {
		datasources = new ArrayList<>();
		ruleSets = new ArrayList<>();
	}

	public void merge(HelioMaterialiserMapping mapping) {
		datasources.addAll(mapping.getDatasources());
		ruleSets.addAll(mapping.getRuleSets());
	}
	
	public List<DataSource> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<DataSource> datasources) {
		this.datasources = datasources;
	}

	public List<RuleSet> getRuleSets() {
		return ruleSets;
	}

	public void setRuleSets(List<RuleSet> ruleSets) {
		this.ruleSets = ruleSets;
	}
	
	
	
	
	
	// -- Ancillary

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datasources == null) ? 0 : datasources.hashCode());
		result = prime * result + ((ruleSets == null) ? 0 : ruleSets.hashCode());
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
		HelioMaterialiserMapping other = (HelioMaterialiserMapping) obj;
		if (datasources == null) {
			if (other.datasources != null)
				return false;
		} else if (!datasources.equals(other.datasources))
			return false;
		if (ruleSets == null) {
			if (other.ruleSets != null)
				return false;
		} else if (!ruleSets.equals(other.ruleSets))
			return false;
		return true;
	}
	
}


