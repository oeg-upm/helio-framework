package helio.framework.mapping;

import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;

import helio.framework.Datasource;



public class DatasourceMapping {

	// -- Attributes
	
	protected String id;
	protected Datasource datasource;
	protected long refreshTime;
	
	// -- CQIndex
	
	public static final SimpleAttribute<DatasourceMapping, String> DATASOURCE_MAPPING_ID = new SimpleAttribute<DatasourceMapping, String>("DATASOURCE_MAPPING_ID") {
	    @Override
		public String getValue(DatasourceMapping datasourceMapping, QueryOptions queryOptions) { 
	    		return datasourceMapping.getId(); 
	    		}
	};
	
	// -- Constructor
	
	public DatasourceMapping() {
		refreshTime = -1;
	}

	// -- Getters & Setters
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	/**
	 * This method returns the refreshing time, however if the refreshing time is -1 it means that this datasource has no updating scheduled
	 * @return
	 */
	public long getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(Long refreshTime) {
		this.refreshTime = refreshTime;
	}
		

	// -- Other Methods

	public Boolean hasScheduledRefresh() {
		return this.getRefreshTime()>0;
	}
	
	// -- Hashcode & Equals

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.id.equals(obj);
	}
	
	// -- toString 

	@Override
	public String toString() {
		return "DatasourceMapping [id=" + id + ", scheduled=" + (hasScheduledRefresh()) + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
