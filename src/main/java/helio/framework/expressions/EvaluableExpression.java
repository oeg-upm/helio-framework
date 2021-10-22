package helio.framework.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import helio.framework.objects.Utils;

public class EvaluableExpression {

	// -- Attributes
	protected String expression;
	protected int[][] referencesPositions;
	protected int referencesStored = 0;
	protected int[][] functionsPositions;
	protected int functionsStored = 0;
	public static final char EXPRESION_FUNCTION_TOKEN_STARTS = '[';
	public static final char EXPRESION_FUNCTION_TOKEN_ENDS = ']';
	public static final char ARGUMENT_TOKEN = '\'';
	protected static final char EXPRESION_TOKEN_BEGIN = '{';
	protected static final char EXPRESION_TOKEN_END = '}';
	protected static final char EXPRESION_TOKEN_IGNORE = '\\';

	// -- Constructors

	public EvaluableExpression(String expression) {
		if (expression == null || expression.isEmpty())
			throw new IllegalArgumentException("Expression provided in constructor must not be null");
		this.expression = expression;
		if(!expression.isEmpty()) {
			int maxSize = (int) StrictMath.ceil(expression.length() / 3);
			this.referencesPositions = new int[maxSize][2];
			findDataReferences();
			maxSize = (int) StrictMath.ceil(expression.length() / 3);
			this.functionsPositions = new int[maxSize][2];
			findFunctionReferences();
		}

	}

	// -- Getters & Setters

	public String getExpression() {
		return expression;
	}

	/**
	 * This function retrieves the data references, those between '{' and '}', from
	 * the expression. The returned data references are not enclosed between the
	 * characters '{' and '}'
	 * 
	 * @return a {@link List} containing the data references, it is empty if no
	 *         references are found
	 */
	public List<String> getDataReferences() {
		List<String> dataReferences = new ArrayList<>();
		for (int index = 0; index < this.referencesStored; index++) {
			int[] positions = referencesPositions[index];
			dataReferences.add(this.expression.substring(positions[0] + 1, positions[1] - 1));
		}
		return dataReferences;
	}

	/**
	 * This function retrieves the function references, those between '[' and ']',
	 * from the expression. The returned function references are not enclosed
	 * between the characters '[' and ']'
	 * 
	 * @return a {@link List} containing the function references, it is empty if no
	 *         references are found.
	 */
	public List<String> getFunctionReferences() {
		List<String> dataReferences = new ArrayList<>();
		for (int index = 0; index < this.functionsStored; index++) {
			int[] positions = functionsPositions[index];
			dataReferences.add(this.expression.substring(positions[0] + 1, positions[1] - 1));
		}
		return dataReferences;
	}

	public int getNumberOfDataReferences() {
		return this.referencesStored;
	}

	public boolean hasDataReferences() {
		return this.referencesStored != 0;
	}

	public int getNumberOfFunctionReferences() {
		return this.functionsStored;
	}

	public boolean hasFunctionReferences() {
		return this.functionsStored != 0;
	}

	// -- Instantiation methods
	/**
	 * This method expects a {@link Map} containing as keys a set of data references
	 * (not enclosed between '{' and '}') and as values the values of those
	 * references. The output of this function is an evaluable expression that is
	 * half-processed, i.e., the data references that are not inside a function
	 * reference are returned directly as constants, instead, those in a function
	 * reference are returned as arguments.<br>
	 * For instance, for this input
	 * "http://domain-sample.com/resource/[TRIM({code})]/{code}" where the data
	 * reference "code" has as value "XYY", this method will output
	 * "http://domain-sample.com/resource/[TRIM('XYY')]/XYY"
	 * 
	 * @param dataValues
	 *            a {@link Map} containing the data references and their values
	 * @return a {@link String} that is an evaluable expression
	 * @throws EvaluableExpressionException
	 *             is thrown if one of the references in the EvaluableExpression is
	 *             not contained in the data values
	 */
	public String instantiateExpression(Map<String, String> dataValues) throws EvaluableExpressionException {
		String expressionCopy = this.expression;
		if (dataValues != null && !dataValues.isEmpty() && referencesStored > 0) {
			for (int index = this.referencesStored - 1; index > -1; index--) {
				int[] positions = referencesPositions[index];
				String reference = this.expression.substring(positions[0] + 1, positions[1] - 1);
				if (dataValues.containsKey(reference)) {
					StringBuilder builder = new StringBuilder();
					String value = dataValues.get(reference);
					builder.append(expressionCopy.substring(0, positions[0]));
					String encloser = "";
					if (bindedDataReference(positions))
						encloser = "'";
					builder.append(encloser).append(value).append(encloser);
					builder.append(expressionCopy.substring(positions[1], expressionCopy.length()));
					expressionCopy = builder.toString();
				} else {
					// TODO: here we could add a strict parameter to allow wrong evaluable
					// expressions to be generated, or to replace the wrong with empty
					throw new EvaluableExpressionException(Utils.buildMessage("The evaluable expression ",
							this.expression, " contains a data reference that does not exist in raw data"));
				}
			}
		}
		return expressionCopy;
	}

	private boolean bindedDataReference(int[] dataRefPositions) {
		boolean isInRange = false;
		for (int index = 0; index < this.functionsStored; index++) {
			int[] functionRefPositions = this.functionsPositions[index];
			isInRange = functionRefPositions[0] < dataRefPositions[0];
			isInRange &= dataRefPositions[1] < functionRefPositions[1];
			if (isInRange)
				break;
		}
		return isInRange;
	}

	// -- Data references

	/**
	 * This method returns a list of raw data access filters/references within and
	 * IRI. A filter/reference can be an XPath expression, a JSONPath expression,...
	 * 
	 * The reference is found by looking up the characters '{' and '}' one by one,
	 * and only those expressions enclosed in the first level of nesting are
	 * retrieved. This makes possible to write nested '{' '}' characters without
	 * causing any trouble
	 * 
	 * @param iriTemplated
	 *            An IRI containing raw data access filters/references enclosed
	 *            between characters '{' and '}'
	 * @return The list of raw data access filters/references within and IRI
	 */
	private void findDataReferences() {
		int startingIndex = -1;
		char[] expressionChars = this.expression.toCharArray();
		int ignores = 0;
		for (int index = 0; index < expressionChars.length; index++) {
			char charAt = expressionChars[index];
			if (charAt == EXPRESION_TOKEN_BEGIN && ignores == 0) { // found a '{'
				startingIndex = index;
				continue;
			} else if (charAt == EXPRESION_TOKEN_END && ignores == 0 && startingIndex != -1) { // found a '}'
				referencesPositions[this.referencesStored][0] = startingIndex;
				referencesPositions[this.referencesStored][1] = index + 1;
				this.referencesStored++;
				startingIndex = -1;
				continue;
			}
			if (charAt == EXPRESION_TOKEN_IGNORE) {
				ignores++;
			} else {
				ignores = 0;
			}
		}
	}

	private void findFunctionReferences() {
		int startingIndex = -1;
		int nesting = 0;
		char[] expressionChars = this.expression.toCharArray();

		boolean quotations = false;
		char previousChar = 'ß';
		for (int index = 0; index < expressionChars.length; index++) {
			char charAt = expressionChars[index];
			if (charAt == ARGUMENT_TOKEN && previousChar != EXPRESION_TOKEN_IGNORE)
				quotations = !quotations;
			if (charAt == EXPRESION_FUNCTION_TOKEN_STARTS && previousChar != EXPRESION_TOKEN_IGNORE && !quotations) {
				// found a '{'
				if (nesting == 0) // store the starting index of the reference
					startingIndex = index;
				nesting++; // increment the nesting level
				continue;
			} else if (charAt == EXPRESION_FUNCTION_TOKEN_ENDS && previousChar != EXPRESION_TOKEN_IGNORE
					&& !quotations) { // found a '}'
				nesting--; // decrement the nesting
				if (nesting == 0) { // reference found
					functionsPositions[this.functionsStored][0] = startingIndex;
					functionsPositions[this.functionsStored][1] = index + 1;
					this.functionsStored++;
				}
			}
			previousChar = charAt;

		}

	}

	// -- Ancillary methods

	@Override
	public String toString() {
		return "Expression [expression=" + expression + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		return true;
	}

}
