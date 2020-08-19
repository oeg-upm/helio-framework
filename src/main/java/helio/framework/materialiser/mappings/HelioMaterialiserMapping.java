package helio.framework.materialiser.mappings;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the mappings required by the {@link MaterialiserEngine} to generate the RDF.<p> 
 * The {@link HelioMaterialiserMapping} consist on a list of {@link DataSource}, a list of {@link RuleSet}, and a list of {@link LinkRule}.
 * @author cimmino
 *
 */
public class HelioMaterialiserMapping {

	private List<DataSource> datasources;
	private List<RuleSet> ruleSets;
	private List<LinkRule> linkRules;
	
	/**
	 * Initializes an empty mapping
	 */
	public HelioMaterialiserMapping() {
		datasources = new ArrayList<>();
		ruleSets = new ArrayList<>();
		linkRules = new ArrayList<>();
	}
	
	/**
	 * This method merges the provided mapping with the one codified by this object
	 * @param mapping a {@link HelioMaterialiserMapping} to be merged with this
	 */
	public void merge(HelioMaterialiserMapping mapping) {
		datasources.addAll(mapping.getDatasources());
		ruleSets.addAll(mapping.getRuleSets());
		linkRules.addAll(mapping.getLinkRules());
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
	
	public List<LinkRule> getLinkRules() {
		return linkRules;
	}

	public void setLinkRules(List<LinkRule> linkRules) {
		this.linkRules = linkRules;
	}

	// -- Ancillary
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datasources == null) ? 0 : datasources.hashCode());
		result = prime * result + ((linkRules == null) ? 0 : linkRules.hashCode());
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
		if (linkRules == null) {
			if (other.linkRules != null)
				return false;
		} else if (!linkRules.equals(other.linkRules))
			return false;
		if (ruleSets == null) {
			if (other.ruleSets != null)
				return false;
		} else if (!ruleSets.equals(other.ruleSets))
			return false;
		return true;
	}
	



	
}


