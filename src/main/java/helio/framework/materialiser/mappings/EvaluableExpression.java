package helio.framework.materialiser.mappings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluableExpression {

	// - Attributes
	
	private String expression;
	private List<String> dataReferences;
	
	// -- Constructor
	
	public EvaluableExpression() {
		dataReferences = new ArrayList<>();
	}
	
	public EvaluableExpression(String expression) {
		this.expression = expression;
		dataReferences = new ArrayList<>();
		extractDataReferences();
	}
	
	// -- Getters & Setters
	
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
		extractDataReferences();
	}

	public List<String> getDataReferences() {
		return dataReferences;
	}
	
	// -- Instantiation methods

	public String instantiateExpression(Map<String,String> dataValues) {
		String expressionCopy =null;
		if(dataReferences.isEmpty()) {
			expressionCopy = this.expression;
		}else if (!dataReferences.isEmpty() && dataValues!=null && !dataValues.isEmpty()) {
			expressionCopy = this.expression;
			for(int index=0;index<dataReferences.size();index++) {
				String reference = dataReferences.get(index);
				String value = dataValues.get(reference);
				if(value!=null) {
					expressionCopy = expressionCopy.replace(reference, dataValues.get(reference));
				}else {
					expressionCopy =null;
					break;
				}
			}
		}
		return expressionCopy;
	}
	


	
	// -- Ancillary methods
	
	@Override
	public String toString() {
		return "Expression [expression=" + expression + ", dataAccess=" + dataReferences + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataReferences == null) ? 0 : dataReferences.hashCode());
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
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
		EvaluableExpression other = (EvaluableExpression) obj;
		if (dataReferences == null) {
			if (other.dataReferences != null)
				return false;
		} else if (!dataReferences.equals(other.dataReferences))
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		return true;
	}
	
	// -- Data references

	/**
	 * This method returns a list of raw data access filters/references within and
	 * IRI. A filter/reference can be an XPath expression, a JSONPath expression,...
	 * 
	 * @param iriTemplated
	 *            An IRI containing raw data access filters/references enclosed
	 *            between characters '{' and '}'
	 * @return The list of raw data access filters/references within and IRI
	 */
	private void extractDataReferences() {
		// 1. Compile pattern to find identifiers
		Pattern pattern = Pattern.compile("\\{[^\\}]+\\}");
		Matcher matcher = pattern.matcher(expression);
		// 2. Find identifiers with compiled matcher from pattern
		while (matcher.find()) {
			String newIdentifier = matcher.group();
			// 2.1 Clean identifier found
			newIdentifier = newIdentifier.replace("{", "").replace("}", "").trim();
			// 2.2 Add new identifier
			if (!dataReferences.contains(newIdentifier))
				dataReferences.add(newIdentifier);
		}

	}
	
	
	
	
}
