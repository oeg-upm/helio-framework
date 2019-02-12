package helio.framework.mapping;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Mapping{
	
	// -- Attributes
	private List<DatasourceMapping> datasources;
	private List<ResourceRule> resourceRules; // as key a ResourceRule and as values the datasources that they consume
	private List<RelationshipRule> relationships; // this variable contains what it is needed to generate object properties between resources of different mapping specifications
	
	// -- Constructor
	
	public Mapping() {	
		resourceRules = new CopyOnWriteArrayList<>();
		relationships = new CopyOnWriteArrayList<>();
		datasources = new CopyOnWriteArrayList<>();
	}

	public List<Object> getResourceRulesByDatasourceId(String datasourceId){
		return this.getResourceRules()
				 .parallelStream()
				 .filter(resourceRule -> resourceRule.getDatasourcesId().contains(datasourceId))
				 .collect(Collectors.toList());
	}

	public List<ResourceRule> getResourceRules() {
		return resourceRules;
	}

	public List<RelationshipRule> getRelationships() {
		return relationships;
	}

	public List<DatasourceMapping> getDatasources() {
		return datasources;
	}
	public DatasourceMapping getDatasourceById(String datasourceId) {
		DatasourceMapping result = null;
		Optional<DatasourceMapping> datasourceFound = this.datasources.parallelStream().filter(datasourceMapped -> datasourceId.equals(datasourceMapped.getId())).findFirst();
		if(datasourceFound.isPresent())
			result = datasourceFound.get();
		return result;
	}
	
	
	public void addMapping(Mapping mapping) {
		// TODO: check that there is not a common key in both mappings
		this.resourceRules.addAll(mapping.getResourceRules());
		this.datasources.addAll(mapping.getDatasources());
		this.relationships.addAll(mapping.getRelationships());
	}
	
	
	
}
