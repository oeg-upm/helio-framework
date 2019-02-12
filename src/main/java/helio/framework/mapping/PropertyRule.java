package helio.framework.mapping;

public class PropertyRule{
	
	// -- Attributes
	
	protected String object; // mandatory
	protected String predicate; // mandatory
	protected Boolean isLiteral; // mandatory
	
	protected String dataType; // optional; disjoint with language

	// -- Constructor
	
	public PropertyRule() {
		super();
	}

	// -- Getters and Setters

	public String getObject() {
		return object;
	}


	public void setObject(String expression) {
		this.object = expression;
	}


	public String getPredicate() {
		return predicate;
	}


	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Boolean getIsLiteral() {
		return isLiteral;
	}

	public void setIsLiteral(Boolean isLiteral) {
		this.isLiteral = isLiteral;
	}

	// Other methods
	
	@Override
	public String toString() {
		return "Property <"+predicate+"> "+ object + " " + isLiteral+", dataType=" + dataType + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((isLiteral == null) ? 0 : isLiteral.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
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
		PropertyRule other = (PropertyRule) obj;
		if (dataType == null) {
			if (other.dataType != null)
				return false;
		} else if (!dataType.equals(other.dataType))
			return false;
		if (isLiteral == null) {
			if (other.isLiteral != null)
				return false;
		} else if (!isLiteral.equals(other.isLiteral))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (predicate == null) {
			if (other.predicate != null)
				return false;
		} else if (!predicate.equals(other.predicate))
			return false;
		return true;
	}

	
	

	
	
	
	
	
	
}
